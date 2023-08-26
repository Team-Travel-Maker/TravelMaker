package com.app.travelmaker.service.member;

import com.app.travelmaker.constant.JoinCheckType;
import com.app.travelmaker.constant.MemberJoinAccountType;
import com.app.travelmaker.constant.Role;
import com.app.travelmaker.domain.member.OAuthAttributes;
import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.domain.member.response.MemberJoinResponseDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.provider.MemberDetail;
import com.app.travelmaker.provider.MemberOauthDetail;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.service.MemberSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public void join(MemberRequestDTO memberRequestDTO, PasswordEncoder passwordEncoder) {
        if(memberRequestDTO.getMemberPw() != null){
            memberRequestDTO.setMemberPw(passwordEncoder.encode(memberRequestDTO.getMemberPw()));
        }
        memberRepository.save(toEntity(memberRequestDTO));
    }

    @Override
    @Transactional
    public void join(MemberRequestDTO memberRequestDTO) {
        memberRepository.findByMemberEmail(memberRequestDTO.getMemberEmail()).ifPresent(member -> {
            memberRepository.oauthJoin(memberRequestDTO);
        });
    }

    @Override
    public Member findByMemberEmail(String memberEmail) {
        return memberRepository.findByMemberEmail(memberEmail).orElseThrow(()-> {throw new RuntimeException("회원을 찾을 수 없습니다");});
    }

    @Override
    public JoinCheckType memberCheckForOauthAndLogin(String memberEmail) {

        AtomicReference<JoinCheckType> joinCheckType = new AtomicReference<>();

        Optional<MemberJoinResponseDTO> memberJoinResponseDTO = memberRepository.memberCheckForOauthAndLogin(memberEmail);

        memberJoinResponseDTO.ifPresentOrElse((member)->{
            log.info(member.getMemberJoinAccountType().getCode());
            if (member.getMemberJoinAccountType().getCode().equals(MemberJoinAccountType.KAKAO.getCode()) ||
                    member.getMemberJoinAccountType().getCode().equals(MemberJoinAccountType.NAVER.getCode())
            ) {
                joinCheckType.set(JoinCheckType.SNS);
            }else{
                joinCheckType.set(JoinCheckType.FALSE);
            }
        },()->{
            joinCheckType.set(JoinCheckType.TRUE);
        });

        return  joinCheckType.get();
    }


    //    spring security에서 DBMS의 회원 정보를 가져올 때 사용될 메소드
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(() -> new BadCredentialsException(username + " not found"));

        if(member.isDeleted()){throw new InternalAuthenticationServiceException("탈퇴한 회원입니다.");};

        return MemberDetail.builder()
                .id(member.getId())
                .memberId(member.getMemberEmail())
                .memberPassword(member.getMemberPw())
                .memberRole(member.getMemberRole())
                .memberResponseDTO(new MemberResponseDTO(member))
                .build();
    }


    @Override
    public List<MemberJoinResponseDTO> findByMemberPhone(String memberPhoneNumber) {
        return memberRepository.findMemberEmailByMemberPhone(memberPhoneNumber);
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //        로그인 완료 후 정보를 담기 위한 준비
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        //        로그인된 사용자의 정보 불러오기
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //        어떤 기업의 OAuth를 사용했는 지의 구분(naver, kakao, mac, facebook,...)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);

        if(member.getId() == null){
            memberRepository.save(member);
        }else{
            member.update(attributes.getName(), attributes.getSnsProfile(), attributes.getEmail());
        }

        return new MemberOauthDetail(new MemberResponseDTO(member),Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getSecurityRole())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    @Transactional
    public Member saveOrUpdate(OAuthAttributes attributes){

        Member memberForSavingOrUpdating = memberRepository.findByMemberEmail(attributes.getEmail())
                .map(member -> {
                    if(member.getMemberJoinAccountType().equals(MemberJoinAccountType.KAKAO)){
                        member.update(attributes.getName(), attributes.getSnsProfile(), attributes.getEmail());
                    }else{
                        member.update(attributes.getName(), attributes.getSnsProfile(), attributes.getEmail(),attributes.getMobile());
                    }
                    return member;
                }).orElse(attributes.toEntity());

        return memberForSavingOrUpdating;
    }

    @Override
    public void resetPw(Long id, String password) {
        memberRepository.resetPw(id, password);
    }

    @Override
    public Page<MemberResponseDTO> getList(Pageable pageable) {
        return memberRepository.getList(pageable);
    }

    @Override
    public void modifyStatus(List<Long> statusIds) {
        statusIds.forEach(id ->memberRepository.modifyStatus(id));
    }

    @Override
    public void modifyType(List<Long> typeIds) {
        typeIds.forEach(id ->memberRepository.modifyType(id));
    }

    @Override
    public void modifyAdmin(List<Long> adminIds) {
        adminIds.forEach(id ->memberRepository.modifyAdmin(id));
    }


    @Override
    public Long findIdByMemberEmail(String memberEmail) {
        return memberRepository.findIdByMemberEmail(memberEmail).orElseThrow(()->{throw new RuntimeException("아이디를 찾을 수 없습니다");});
    }

    @Override
    public String certifiedPhoneNumber(String to) {
        String api_key = "NCSAIGM8SGCYHUGE";
        String api_secret = "IUDYW1NN4DROTS0VHS2IZLAC4GB77FZT";
        Message coolsms = new Message(api_key, api_secret);

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", to);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
        params.put("from", "01040573327");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + numStr + "] 입니다.");

        try {
            org.json.simple.JSONObject obj = (org.json.simple.JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        return numStr;
    }
}

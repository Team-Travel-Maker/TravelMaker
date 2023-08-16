package com.app.travelmaker.service.member;

import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.provider.MemberDetail;
import com.app.travelmaker.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void join(MemberRequestDTO memberRequestDTO, PasswordEncoder passwordEncoder) {
        memberRequestDTO.setMemberPw(passwordEncoder.encode(memberRequestDTO.getMemberPw()));
        memberRepository.save(toEntity(memberRequestDTO));
    }

    @Override
    public boolean checkId(String memberEmail) {
        AtomicBoolean checkResult = new AtomicBoolean(false);
        Optional<Member> foundMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        foundMemberEmail.ifPresentOrElse((member) -> checkResult.set(true),()-> checkResult.set(false));
        return checkResult.get();
    }

    //    spring security에서 DBMS의 회원 정보를 가져올 때 사용될 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return MemberDetail.builder()
                .memberId(member.getMemberEmail())
                .memberPassword(member.getMemberPw())
                .memberRole(member.getMemberRole())
                .build();
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

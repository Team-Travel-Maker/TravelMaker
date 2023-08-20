package com.app.travelmaker.controller.account;

import com.app.travelmaker.constant.JoinCheckType;
import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("api/accounts/*")
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
public class AccountApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;

    /*아이디 중복 체크*/
    @PostMapping("check")
    public JoinCheckType checkId(String memberEmail){
        return memberService.memberCheckForOauthAndLogin(memberEmail);
    }

    /*일반 회원가입*/
    @PostMapping("join")
    public void join(MemberRequestDTO memberRequestDTO){
        if(memberRequestDTO.getMemberPw() !=null){
            memberService.join(memberRequestDTO, passwordEncoder);
        }else{
            memberService.join(memberRequestDTO);
            /*sns 계정 회원가입 할때 다시 session 에 넣어주기*/
            Member member = memberService.findByMemberEmail(memberRequestDTO.getMemberEmail());

            MemberResponseDTO memberResponseDTO = new MemberResponseDTO(member);

            session.setAttribute("member", memberResponseDTO);

        }
    }



    @GetMapping("/check/sendSMS")
    public String sendSMS(String to) {
        return memberService.certifiedPhoneNumber(to);
    }

}

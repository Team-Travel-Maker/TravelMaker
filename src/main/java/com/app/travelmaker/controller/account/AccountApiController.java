package com.app.travelmaker.controller.account;

import com.app.travelmaker.constant.JoinCheckType;
import com.app.travelmaker.domain.member.request.MemberRequestDTO;
import com.app.travelmaker.domain.member.response.MemberJoinResponseDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.service.mail.ChangePwSendMailService;
import com.app.travelmaker.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("api/accounts/*")
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
public class AccountApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;
    private final ChangePwSendMailService changePwSendMailService;

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
 /*           Member member = memberService.findByMemberEmail(memberRequestDTO.getMemberEmail());

            MemberResponseDTO memberResponseDTO = new MemberResponseDTO(member);

            session.setAttribute("member", memberResponseDTO);*/

        }
    }

    @PostMapping("find-id")
    public List<MemberJoinResponseDTO> findId(String memberPhoneNumber){
        return memberService.findByMemberPhone(memberPhoneNumber);
    }


    @GetMapping("/check/sendSMS")
    public String sendSMS(String to) {
        return memberService.certifiedPhoneNumber(to);
    }

    // 비밀번호 재설정 메일전송
    @PostMapping("change-pw")
    public void sendChangePwMail(String memberEmail, Long id) throws Exception{
        changePwSendMailService.sendSimpleMessage(memberEmail, id);
    }

    @PostMapping("/check/email")
    public Long sendMemberId(String memberEmail){
        return  memberService.findIdByMemberEmail(memberEmail);
    }

    @PostMapping("/reset/pw/{id}")
    public void reset(@PathVariable Long id, String newPassword, HttpSession session){
         session.setAttribute("check", id);
        memberService.resetPw(id, newPassword);
    }


}

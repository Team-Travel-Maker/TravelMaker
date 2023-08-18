package com.app.travelmaker.controller.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/accounts/*")
public class AccountController {

    //로그인
    @GetMapping("login/login")
    public void goToLogin(){;}

    @PostMapping("login/login")
    public String processUsername(String memberEmail, RedirectAttributes redirectAttributes) {
        log.info(memberEmail);
        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        return "redirect:/accounts/password/input"; // 비밀번호 입력 페이지로 리다이렉트
    }


    //회원 가입
    @PostMapping("join/join")
    public void goToJoin(String memberEmail, Model model){
        model.addAttribute("memberEmail", memberEmail);
    }

    //계정 찾기
    @GetMapping("account/find")
    public void goToFind(){;}

    //계정 찾기 성공
    @GetMapping("account/found")
    public void goToFound(){;}
    
    //이미 존재하는 계정 확인
    @GetMapping("account/exiting")
    public void goToExiting(){;}

    //계정이 존재 하지 않을 때
    @GetMapping("account/not-exiting")
    public void goToNotExiting(){;}

    //비밀번호 입력
    @GetMapping("password/input")
    public void goToPassword(){;}



    //비밀번호 재설정 이메일 전송 확인 화면
    @GetMapping("password/email")
    public void goToPasswordEmail(){;}



    //비밀번호 재설정 화면
    @GetMapping("password/reset")
    public void goToPasswordReset(){;}

    //비밀번호 재설정 성공 화면
    @GetMapping("password/reset-ok")
    public void goToPasswordResetOk(){;}
}

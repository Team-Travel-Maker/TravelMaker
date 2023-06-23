package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/login/*")
public class LoginController {

    //로그인
    @GetMapping("login")
    public void goToLogin(){;}

    //회원 가입
    @GetMapping("join")
    public void goToJoin(){;}

    //계정 찾기
    @GetMapping("find")
    public void goToFind(){;}

    //계정 찾기 성공
    @GetMapping("found")
    public void goToFound(){;}
    
    //이미 존재하는 계정 확인
    @GetMapping("exiting")
    public void goToExiting(){;}

    //비밀번호 입력
    @GetMapping("password")
    public void goToPassword(){;}

    //비밀번호 재설정 이메일 전송 확인 화면
    @GetMapping("password_email")
    public void goToPasswordEmail(){;}

    //비밀번호 재설정 화면
    @GetMapping("password_reset")
    public void goToPasswordReset(){;}

    //비밀번호 재설정 성공 화면
    @GetMapping("password_reset_ok")
    public void goToPasswordResetOk(){;}
}

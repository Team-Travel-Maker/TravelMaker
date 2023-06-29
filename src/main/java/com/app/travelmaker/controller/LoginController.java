package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/accounts/*")
public class LoginController {

    //로그인
    @GetMapping("login/login")
    public void goToLogin(){;}

    //회원 가입
    @GetMapping("join/join")
    public void goToJoin(){;}

    //계정 찾기
    @GetMapping("account/find")
    public void goToFind(){;}

    //계정 찾기 성공
    @GetMapping("account/found")
    public void goToFound(){;}
    
    //이미 존재하는 계정 확인
    @GetMapping("account/exiting")
    public void goToExiting(){;}

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

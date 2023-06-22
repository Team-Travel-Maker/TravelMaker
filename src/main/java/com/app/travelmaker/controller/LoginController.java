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
}

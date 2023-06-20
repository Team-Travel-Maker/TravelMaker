package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admins/*")
public class AdminController {

//    회원관리
    @GetMapping("member/list")
    public void goToMemberList(){;}


    @GetMapping("member/detail")
    public void goToMemberDetail(){;}

    @GetMapping("member/modify")
    public void goToMemberModify(){;}

// 공지사항 관리



}

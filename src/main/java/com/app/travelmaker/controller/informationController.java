package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/informations/*")
public class informationController {

    // 고객 센터 메인 화면
    @GetMapping("main")
    public void goToInformationMain(){;}

    // 공지 사항 목록
    @GetMapping("notice/list")
    public void goToNoticeList(){;}

    // 공지 사항 상세
    @GetMapping("notice/detail")
    public void goToNoticeDetail(){;}

    // 문의 / 신고 등록 화면
    @GetMapping("inquiry/write")
    public void goToInquiryWrite(){;}

}

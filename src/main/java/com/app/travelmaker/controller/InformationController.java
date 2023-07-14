package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/informations/*")
public class InformationController {

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

    // 일반회원 자주 묻는 질문 목록
    @GetMapping("faq/general-list")
    public void goToFAQGeneralList(){;}

    // 업체회원 자주 묻는 질문 목록
    @GetMapping("faq/company-list")
    public void goToFAQCompanyList(){;}



    //-------일단 정적 페이지 양식만! 나중에 정적으로 여러개 작성할 예정---------------
    // 일반회원 자주 묻는 질문 상세
    @GetMapping("faq/general-detail")
    public void goToFAQGeneralDetail(){;}

    // 일반회원 자주 묻는 질문 상세
    @GetMapping("faq/company-detail")
    public void goToFAQCompanyDetail(){;}

}

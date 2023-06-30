package com.app.travelmaker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/community/*")
public class CommunityController {

//    이용 후기
    @GetMapping("review/list")
    public void goToCommunityList(){;}

    @GetMapping("review/write")
    public void goToWrite(){;}

    @GetMapping("review/detail")
    public void goToDetail(){;}

//    개선 요청
//    목록 작성 상세 수정
//    소통
}

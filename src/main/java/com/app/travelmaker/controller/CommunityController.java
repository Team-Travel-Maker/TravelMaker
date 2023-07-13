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
    public void goToCReviewList(){;}

    @GetMapping("review/write")
    public void goToReviewWrite(){;}

    @GetMapping("review/detail")
    public void goToReviewDetail(){;}

//    개선 요청
    @GetMapping("request/list")
    public void goToRequestList(){;}

    @GetMapping("request/write")
    public void goToRequestWrite(){;}

    @GetMapping("request/detail")
    public void goToRequestDetail(){;}

//    소통
    @GetMapping("board/list")
    public void goToBoardList(){;}

    @GetMapping("board/detail")
    public void goToBoardDetail(){;}
    @GetMapping("board/write")
    public void goToBoardWrite(){;}

}

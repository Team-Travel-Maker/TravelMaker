package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/mypage/*")
public class MyPageController {

//    내가 작성한 게시물
    @GetMapping("my/posts")
    public void goToPosts(){;}



//  포인트
    @GetMapping("points")
    public void goToPoints(){
        ;
    }
//  북마크
    @GetMapping("bookmarks")
    public void goToBookmarks(){;}
//  좋아요
    @GetMapping("likes")
    public void goToLikes(){;}
//  보유 상품권?
    @GetMapping("giftCard")
    public void goToGiftCard(){;}
}

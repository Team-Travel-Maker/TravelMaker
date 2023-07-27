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

    // 내가 작성한 댓글
    @GetMapping("my/comments")
    public void goToComments(){;}


//   알림
    @GetMapping("alarm")
    public void goToAlarm(){;}
// 관심 지역 설정
    @GetMapping("interestsRegion")
    public void goTointerestsRegion(){;}
// 계정설정
    @GetMapping("accountManage")
    public void goToAccountManage(){; }
// 회원탈퇴
    @GetMapping("withdrawal")
    public void goToWithDrawal(){;}
// 업체 등록
    @GetMapping("company/registration")
    public void goToRegistration(){;}

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

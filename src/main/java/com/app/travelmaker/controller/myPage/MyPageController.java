package com.app.travelmaker.controller.myPage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/mypage/*")
public class MyPageController {

    // 마이페이지 메인
    @GetMapping("main")
    public void goToMain(){;}

    // 내가 작성한 게시물
    @GetMapping("my/posts")
    public void goToPosts(){;}

    // 내가 작성한 댓글
    @GetMapping("my/comments")
    public void goToComments(){;}

    //  나의 문의/신고 내역
    @GetMapping("my/list-detail")
    public void goToMyList(){;}



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
    // 업체 등록
    @GetMapping("company/edit")
    public void goToEdit(){;}
// 업체 목록
    @GetMapping("company/list")
    public void goToList(){;}
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

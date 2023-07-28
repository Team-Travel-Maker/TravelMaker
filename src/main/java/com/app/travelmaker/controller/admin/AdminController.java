package com.app.travelmaker.controller.admin;

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

    //목록
    @GetMapping("notice/list")
    public void goToNoticeList(){;}

    //등록
    @GetMapping("notice/write")
    public void goToNoticeWrite(){;}

    //상세
    @GetMapping("notice/detail")
    public void goToNoticeDetail(){;}
    //수정
    @GetMapping("notice/modify")
    public void goToNoticeModify(){;}


//  문의 관리

    //목록
    @GetMapping("inquiry/list")
    public void goToInquiryList(){;}

    //상세 답변 미완료
    @GetMapping("inquiry/detail")
    public void goToInquiryDetail(){;}

    //상세 답변 완료된
    @GetMapping("inquiry/answer-detail")
    public void goToInquiryAnswerDetail(){;}

    //답변 작성
    @GetMapping("inquiry/write")
    public void goToInquiryAnswerWrite(){;}
    //답변 수정
    @GetMapping("inquiry/modify")
    public void goToInquiryAnswerModify(){;}

//    커뮤니티 관리

    //소통 목록
    @GetMapping("community/list")
    public void goToCommunityList(){;}

    //일반 후기 목록
    @GetMapping("review/list")
    public void goToReviewList(){;}

    //개선 요청 목록
    @GetMapping("improvement/list")
    public void goToImprovementList(){;}

//   상품권 관리

    //상품권 목록
    @GetMapping("coupon/list")
    public void goToCouponList(){;}

    //상품권 등록
    @GetMapping("coupon/write")
    public void goToCouponWrite(){;}

    //상품권 상세
    @GetMapping("coupon/detail")
    public void goToCouponDetail(){;}

    //상품권 수정
    @GetMapping("coupon/modify")
    public void goToCouponModify(){;}

//    업체 관리

    // 업체 목록
    @GetMapping("company/list")
    public void goToCompanyList(){;}

    // 업체 상세
    @GetMapping("company/detail")
    public void goToCompanyDetail(){;}

    //업체 신청 답변
    @GetMapping("company/write")
    public void goToCompanyWrite(){;}

    //업체 답변 수정
    @GetMapping("company/modify")
    public void goToCompanyModify(){;}

    //업체 신청 답변 받은 상세
    @GetMapping("company/answer-detail")
    public void goToCompanyAnswerDetail(){;}


}

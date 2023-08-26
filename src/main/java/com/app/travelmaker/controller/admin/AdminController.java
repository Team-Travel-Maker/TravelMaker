package com.app.travelmaker.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping(path = {"notice/detail/{id}", "notice/modify/{id}"})
    public String goToNoticeDetail(@PathVariable(value = "id", required = true) Long id, Model model, HttpServletRequest request){
        model.addAttribute("noticeId", id);
        return "/admins/notice/" + request.getRequestURI().split("/")[3];
        }

    //수정
    @GetMapping("notice/modify")
    public void goToNoticeModify(){;}


//  문의 관리

    //목록
    @GetMapping("inquiry/list")
    public void goToInquiryList(){;}

    //상세 답변 미완료 및 답변 수정
    @GetMapping(path = {"inquiry/detail/{id}","inquiry/modify/{id}"})
    public String goToDetail (@PathVariable(required = true) Long id, Model model, HttpServletRequest request){
        model.addAttribute("customServiceId", id);
        return "/admins/inquiry/" + request.getRequestURI().split("/")[3];
    }

/*    //상세 답변 완료된
    @GetMapping("inquiry/answer-detail")
    public void goToInquiryAnswerDetail(){;}

    //답변 작성
    @GetMapping("inquiry/write")
    public void goToInquiryAnswerWrite(){;}*/

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
    @GetMapping(path = {"coupon/detail/{id}", "coupon/modify/{id}"})
    public String goToCouponDetail(@PathVariable(value = "id", required = true) Long id, Model model, HttpServletRequest request){
        model.addAttribute("couponId", id);
        return "/admins/coupon/" + request.getRequestURI().split("/")[3];
    }




    //상품권 수정
    @GetMapping("coupon/modify")
    public void goToCouponModify(){;}

//    업체 관리

    // 업체 목록
    @GetMapping("store/list")
    public void goToCompanyList(){;}

    // 업체 상세
    @GetMapping("store/detail/{id}")
    public String goToCompanyDetail(@PathVariable Long id, Model model){
        model.addAttribute("storeId", id);
        return "/admins/store/detail";
        }

/*    //업체 신청 답변
    @GetMapping("store/write")
    public void goToCompanyWrite(){;}

    //업체 답변 수정
    @GetMapping("store/modify")
    public void goToCompanyModify(){;}

    //업체 신청 답변 받은 상세
    @GetMapping("store/answer-detail")
    public void goToCompanyAnswerDetail(){;}*/


}

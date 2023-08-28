package com.app.travelmaker.controller.myPage;

import com.app.travelmaker.constant.CsType;
import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.domain.mypage.my.*;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;
import com.app.travelmaker.service.mypage.company.StoreService;
import com.app.travelmaker.service.mypage.my.*;
import com.app.travelmaker.service.mypage.my.account.MyAccountService;
import com.app.travelmaker.service.mypage.my.customService.MyCustomServiceService;
import com.app.travelmaker.service.mypage.my.point.MyPointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/myPages/*")
public class MyPageApiController {

    private final MyGiftCardService myGiftCardService;
    private final StoreService storeService;
    private final MyBookmarkService myBookmarkService;
    private final MyCommunityLikeService myCommunityLikeService;
    private final MyStoryLikeService myStoryLikeService;
    private final MyPointService myPointService;
    private final MyCustomServiceService myCustomServiceService;
    private final MyAccountService myAccountService;
    private final MypageMainService mypageMainService;

    /**
     * 상품권
     */
    // 나의 상품권 목록
    @GetMapping("giftCard")
    public ResponseEntity<?> goMyGiftCard() {
        List<MyGiftCardDTO> myGiftCardDTOS = myGiftCardService.getGiftCardListByMemberId();
        return ResponseEntity.ok(myGiftCardDTOS);
    }

    /**
     * 업체
     */
    // 업체 등록 api
    @Transactional
    @PostMapping("store")
    public ResponseEntity<?> addStore(@RequestBody StoreDTO request) {
        storeService.addStore(request);
        log.info(request.toString());
        return ResponseEntity.ok("/mypage/company/list");
    }

    // 전체 업체 목록 api
    @GetMapping("storeList")
    public ResponseEntity<?> getStoreList() {
        List<StoreDTO> allStore = storeService.getAllStore();
        return ResponseEntity.ok(allStore);
    }

    // 업체 삭제 api
    @Transactional
    @DeleteMapping("store")
    public ResponseEntity<?> deleteStore(@RequestParam Map<String, Object> storeIdList) {

        String jsonToStr = storeIdList.get("storeIdList").toString().replaceAll("\\[|\\]|\"", "");

        List<Long> storeIds = Arrays.stream(jsonToStr.split(","))
                                    .map(i -> Long.parseLong(i))
                                    .collect(Collectors.toList());

        log.info(storeIds + "");
        // 업체 삭제
        storeService.deleteStore(storeIds);

        return ResponseEntity.ok("/mypage/company/list");
    }

    // 수정 업체 정보 api
    @GetMapping("store")
    public ResponseEntity<?> getStore(@RequestParam Long storeId) {
        StoreDTO store = storeService.getStore(storeId);
        return ResponseEntity.ok(store);
    }

    // 업체 수정 api
    @Transactional
    @PutMapping("store")
    public ResponseEntity updateStore(@RequestBody StoreDTO request) {
        storeService.updateStore(request);
        log.info(request.toString());
        return ResponseEntity.ok("/mypage/company/list");
    }

    /**
     * 북마크
     */
    // 북마크 리스트 api
    @GetMapping("bookmarks")
    public ResponseEntity<?> getBookmarks() {
        List<MyBookmarkDTO> bookmarks = myBookmarkService.getBookmarks();
        return ResponseEntity.ok(bookmarks);
    }

    // 북마크 삭제 api
    @DeleteMapping("bookmarks")
    public ResponseEntity<?> deleteBookmark(@RequestParam Long bookmarkId) {
        log.info("북마크 아이디:{}",bookmarkId);
        myBookmarkService.deleteBookmark(bookmarkId);
        return ResponseEntity.ok("/mypage/bookmarks");
    }

    /**
     * 좋아요
     */
    // 커뮤니티 좋아요 리스트 api
    @GetMapping("communityLikes")
    public ResponseEntity<?> getCommunityLikes() {
        List<MyCommunityLikeDTO> communityLikes = myCommunityLikeService.getCommunityLikes();
        return ResponseEntity.ok(communityLikes);
    }

    // 커뮤니티 좋아요 삭제 api
    @DeleteMapping("communityLikes")
    public ResponseEntity<?> deleteCommunityLike(@RequestParam Long communityLikeId) {
        log.info("커뮤니티 좋아요 아이디:{}",communityLikeId);
        myCommunityLikeService.deleteCommunityLike(communityLikeId);
        return ResponseEntity.ok("통신 성공");
    }

    // 스토리 좋아요 리스트 api
    @GetMapping("storyLikes")
    public ResponseEntity<?> getStoryLikes() {
        List<MyStoryLikeDTO> storyLikes = myStoryLikeService.getStoryLikes();
        return ResponseEntity.ok(storyLikes);
    }

    // 스토리 좋아요 삭제 api
    @DeleteMapping("storyLikes")
    public ResponseEntity<?> deleteStoryLike(@RequestParam Long storyLikeId) {
        log.info("스토리 좋아요 아이디:{}",storyLikeId);
        myStoryLikeService.deleteStoryLike(storyLikeId);
        return ResponseEntity.ok("통신 성공");
    }


    /**
     * 포인트
     */
    // 포인트 내역 전체 조회
    @GetMapping("points")
    public ResponseEntity<?> getMyPoints() {
        List<MyPointDTO> myPoints = myPointService.getMyPoints();
        return ResponseEntity.ok(myPoints);
    }

    // 포인트 내역 조회(적립, 사용)
    @GetMapping("pointsByType")
    public ResponseEntity<?> getMyPointsByPointType(@RequestParam String pointType) {
        log.info(pointType);
        PointCateGoryType pointCateGoryType = null;
        if(PointCateGoryType.EARN.getCode().equals(pointType)) {
            pointCateGoryType = PointCateGoryType.EARN;
        } else {
            pointCateGoryType = PointCateGoryType.USE;
        }
        log.info(pointType);
        log.info(pointCateGoryType.toString());
        List<MyPointDTO> myPoints = myPointService.getMyPointsByPointType(pointCateGoryType);
        return ResponseEntity.ok(myPoints);
    }


    /**
     * 문의 신고
     */
    @GetMapping("inquiries")
    public ResponseEntity<?> getMyListFive(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                           @RequestParam(value = "keyword", required = false) String keyword,
                                           @RequestParam(value = "csType", required = false) String csType){
        log.info(keyword);
        Page<CustomServiceResponseDTO> myListFive;
        if(csType == null) {
            myListFive = myCustomServiceService.getMyListFive(pageable, keyword);
        } else {
            log.info(csType);
            if(CsType.INQUIRY.getCode().equals(csType)) {
                myListFive = myCustomServiceService.getMyListFiveByType(pageable, keyword, CsType.INQUIRY);
            } else {
                myListFive = myCustomServiceService.getMyListFiveByType(pageable, keyword, CsType.DECLARATION);
            }
        }
        return ResponseEntity.ok(myListFive);
    }

    /**
     * 알림 설정
     */
    @Transactional
    @PutMapping("alarm")
    public ResponseEntity<?> updateAlarm(@RequestParam(value = "settingValue") boolean settingValue,
                                         @RequestParam(value = "alarm") String alarm){

        if (alarm.equals("emailBenefitEvent")) {
            myAccountService.updateEmailBenefitEventAlarm(settingValue);
        } else if(alarm.equals("emailSuggestion")) {
            myAccountService.updateEmailSuggestionAlarm(settingValue);
        } else {
            myAccountService.updateSnsBenefitEventAlarm(settingValue);
        }

        return ResponseEntity.ok("OK");
    }

    /**
     * 알림 설정
     */
    @Transactional
    @DeleteMapping("withdrawal")
    public ResponseEntity<?> withdrawal(HttpSession session){
        myAccountService.withdrawal();
        session.invalidate();
        return ResponseEntity.ok("/");
    }

    /**
     *  게정 설정
     */
    // 일반회원 프로필 파일 등록 수정
    @Transactional
    @PostMapping("accountManager/uploadProfile")
    public ResponseEntity<?> uploadProfile(@RequestBody FileDTO request) {
        myAccountService.uploadProfile(request);
        log.info(request.toString());
        return ResponseEntity.ok("/mypage/accountManage");
    }

    // 이름 변경
    @Transactional
    @PutMapping("accountManager/name")
    public ResponseEntity<?> updateMemberName(@RequestBody Map<String, String> request){
        myAccountService.updateMemberName(request.get("memberName"));
        return ResponseEntity.ok("/mypage/accountManage");
    }

    // 비번 변경전 비밀번호 확인
    @Transactional
    @PostMapping("accountManager/oldPassword")
    public ResponseEntity<?> checkPassword(@RequestBody Map<String, String> request) {
        Boolean result = myAccountService.checkPassword(request.get("oldPassword"));
        if(result == true) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 비밀번호 변경
    @Transactional
    @PutMapping("accountManager/password")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> request) {
        myAccountService.updatePassword(request.get("newPassword"));
        log.info(request.toString());
        return ResponseEntity.ok("/mypage/accountManage");
    }

    // 전화번호 변경
    @Transactional
    @PutMapping("accountManager/mobile")
    public ResponseEntity<?> updateMobile(@RequestBody Map<String, String> request) {
        myAccountService.updateMobile(request.get("mobile"));
        log.info(request.toString());
        return ResponseEntity.ok("/mypage/accountManage");
    }

    /**
     * 마이페이지
     */
    // 관심지역 등록 (Member테이블 수정)
    @Transactional
    @PutMapping("interestsRegion")
    public ResponseEntity<?> setInterestsRegion(@RequestBody Map<String, String> request) {
        myAccountService.setInterestsRegion(request.get("interestsStr"));
        log.info(request.toString());
        return ResponseEntity.ok("/mypage/main");
    }

    // 등록 업체 개수 가져오기
    @GetMapping("companyCount")
    public ResponseEntity<?> getCompanyCount() {
        Long companyCount = mypageMainService.getCompanyCount();
        return ResponseEntity.ok(companyCount.toString());
    }

    // 문의/신고 개수 가져오기
    @GetMapping("customServiceCount")
    public ResponseEntity<?> getCustomServiceCount() {
        Long customServiceCount = mypageMainService.getCustomServiceCount();
        return ResponseEntity.ok(customServiceCount.toString());
    }

    // 상품권 개수 가져오기
    @GetMapping("giftCardCount")
    public ResponseEntity<?> getGiftCardCount() {
        Long giftCardCount = mypageMainService.getGiftCardCount();
        return ResponseEntity.ok(giftCardCount.toString());
    }

    // 북마크 개수 가져오기
    @GetMapping("bookmarksCount")
    public ResponseEntity<?> getBookmarksCount() {
        Long bookmarksCount = mypageMainService.getBookmarksCount();
        return ResponseEntity.ok(bookmarksCount.toString());
    }

    // 좋아요 개수 가져오기
    @GetMapping("likesCount")
    public ResponseEntity<?> getLikesCount() {
        Long likesCount = mypageMainService.getLikesCount();
        return ResponseEntity.ok(likesCount.toString());
    }

    // 북마크 최대 4개 가져오기
    @GetMapping("bookmarksMax4")
    public ResponseEntity<?> getBookmarksMax4() {
        List<MyBookmarkDTO> bookmarksMax4 = mypageMainService.getBookmarksMax4();
        return ResponseEntity.ok(bookmarksMax4);
    }

    // 좋아요 최대 6개 가져오기
    @GetMapping("likesMax6")
    public ResponseEntity<?> getLikesMax4() {
        List<MyLikeDTO> likesMax6 = mypageMainService.getLikesMax6();
        return ResponseEntity.ok(likesMax6);
    }
}

package com.app.travelmaker.controller.myPage;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;
import com.app.travelmaker.domain.mypage.my.MyCommunityLikeDTO;
import com.app.travelmaker.domain.mypage.my.MyGiftCardDTO;
import com.app.travelmaker.domain.mypage.my.MyStoryLikeDTO;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;
import com.app.travelmaker.service.mypage.company.StoreService;
import com.app.travelmaker.service.mypage.my.MyBookmarkService;
import com.app.travelmaker.service.mypage.my.MyCommunityLikeService;
import com.app.travelmaker.service.mypage.my.MyGiftCardService;
import com.app.travelmaker.service.mypage.my.MyStoryLikeService;
import com.app.travelmaker.service.mypage.my.point.MyPointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    // 나의 상품권 목록
    @GetMapping("giftCard")
    public ResponseEntity<?> goMyGiftCard() {
        List<MyGiftCardDTO> myGiftCardDTOS = myGiftCardService.getGiftCardListByMemberId();
        return ResponseEntity.ok(myGiftCardDTOS);
    }

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
    @GetMapping("/store")
    public ResponseEntity<?> getStore(@RequestParam Long storeId) {
        StoreDTO store = storeService.getStore(storeId);
        return ResponseEntity.ok(store);
    }

    // 업체 수정 api
    @Transactional
    @PutMapping("/store")
    public ResponseEntity updateStore(@RequestBody StoreDTO request) {
        storeService.updateStore(request);
        log.info(request.toString());
        return ResponseEntity.ok("/mypage/company/list");
    }

    // 북마크 리스트 api
    @GetMapping("/bookmarks")
    public ResponseEntity<?> getBookmarks() {
        List<MyBookmarkDTO> bookmarks = myBookmarkService.getBookmarks();
        return ResponseEntity.ok(bookmarks);
    }

    // 북마크 삭제 api
    @DeleteMapping("/bookmarks")
    public ResponseEntity<?> deleteBookmark(@RequestParam Long bookmarkId) {
        log.info("북마크 아이디:{}",bookmarkId);
        myBookmarkService.deleteBookmark(bookmarkId);
        return ResponseEntity.ok("/mypage/bookmarks");
    }

    // 커뮤니티 좋아요 리스트 api
    @GetMapping("/communityLikes")
    public ResponseEntity<?> getCommunityLikes() {
        List<MyCommunityLikeDTO> communityLikes = myCommunityLikeService.getCommunityLikes();
        return ResponseEntity.ok(communityLikes);
    }

    // 커뮤니티 좋아요 삭제 api
    @DeleteMapping("/communityLikes")
    public ResponseEntity<?> deleteCommunityLike(@RequestParam Long communityLikeId) {
        log.info("커뮤니티 좋아요 아이디:{}",communityLikeId);
        myCommunityLikeService.deleteCommunityLike(communityLikeId);
        return ResponseEntity.ok("통신 성공");
    }

    // 스토리 좋아요 리스트 api
    @GetMapping("/storyLikes")
    public ResponseEntity<?> getStoryLikes() {
        List<MyStoryLikeDTO> storyLikes = myStoryLikeService.getStoryLikes();
        return ResponseEntity.ok(storyLikes);
    }

    // 스토리 좋아요 삭제 api
    @DeleteMapping("/storyLikes")
    public ResponseEntity<?> deleteStoryLike(@RequestParam Long storyLikeId) {
        log.info("스토리 좋아요 아이디:{}",storyLikeId);
        myStoryLikeService.deleteStoryLike(storyLikeId);
        return ResponseEntity.ok("통신 성공");
    }

    // 포인트 내역 전체 조회
    @GetMapping("/points")
    public ResponseEntity<?> getMyPoints() {
        List<MyPointDTO> myPoints = myPointService.getMyPoints();
        return ResponseEntity.ok(myPoints);
    }

    // 포인트 내역 조회(적립, 사용)
    @GetMapping("/pointsByType")
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
}

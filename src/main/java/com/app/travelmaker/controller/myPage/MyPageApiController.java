package com.app.travelmaker.controller.myPage;

import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;
import com.app.travelmaker.domain.mypage.my.MyGiftCardDTO;
import com.app.travelmaker.service.mypage.company.StoreService;
import com.app.travelmaker.service.mypage.my.MyBookmarkService;
import com.app.travelmaker.service.mypage.my.MyGiftCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
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

    @GetMapping("giftCard")
    public ResponseEntity<?> goMyPagePoint() {
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

    // 북마크 리스트 api
    @GetMapping("/bookmarks")
    public ResponseEntity<?> getBookmarks() {
        List<MyBookmarkDTO> bookmarks = myBookmarkService.getBookmarks();
        return ResponseEntity.ok(bookmarks);
    }

    // 북마크 삭제 api
    @DeleteMapping("/bookmarks")
    public ResponseEntity<?> getBookmarks(@RequestParam Long bookmarkId) {
        log.info("북마크 아이디:{}",bookmarkId);
        myBookmarkService.deleteBookmark(bookmarkId);
        return ResponseEntity.ok("/mypage/bookmarks");
    }

    // 좋아요 리스트 api
    @GetMapping("/likes")
    public ResponseEntity<?> getLikes() {
        List<MyBookmarkDTO> bookmarks = myBookmarkService.getBookmarks();
        return ResponseEntity.ok(bookmarks);
    }
}

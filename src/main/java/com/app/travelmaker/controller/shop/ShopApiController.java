package com.app.travelmaker.controller.shop;

import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.service.shop.GiftCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/shops/*")
public class ShopApiController {

    private final GiftCardService giftCardService;

    @GetMapping("{giftCardRegion}")
    public ResponseEntity<?> getGiftCardListByGiftCardRegion(@PathVariable String giftCardRegion) {
        List<GiftCardDTO> giftCardDTOS = giftCardService.getGiftCardListByGiftCardRegion(giftCardRegion);
        return ResponseEntity.ok(giftCardDTOS);
    }

    @GetMapping
    public ResponseEntity<?> getGiftCardList() {
        List<GiftCardDTO> giftCardDTOS = giftCardService.getGiftCardList();
        return ResponseEntity.ok(giftCardDTOS);
    }

    // 상품권 구매 api
    @Transactional
    @PostMapping
    public ResponseEntity<?> purchaseGiftCard(@RequestBody PurchaseRequestDTO request) {
        giftCardService.addPurchase(request);
        return ResponseEntity.ok("/mypage/points/");
    }
}

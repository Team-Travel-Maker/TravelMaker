package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.file.FileSize;
import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.entity.giftcard.GiftCard;
import com.app.travelmaker.service.shop.GiftCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
@RequestMapping("api/admins/coupon")
public class CouponAdminApiController {

    private final GiftCardService giftCardService;

    @GetMapping("")
    public Page<GiftCardDTO> getList(@PageableDefault(page = 0,size = 10)Pageable pageable){
        return giftCardService.getListWithPage(pageable);
    }

    @PostMapping("")
    public void register(@RequestPart GiftCardDTO giftCardDTO){
        giftCardService.register(giftCardDTO);
    }

}

package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.service.shop.GiftCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
@RequestMapping("api/admins/coupon")
public class GiftCardAdminApiController {

    private final GiftCardService giftCardService;

    @GetMapping("")
    public Page<GiftCardDTO> getList(@PageableDefault(page = 0,size = 10)Pageable pageable){
        return giftCardService.getListWithPage(pageable);
    }

    @PostMapping("")
    public void register(@RequestPart GiftCardDTO giftCardDTO){
        giftCardService.register(giftCardDTO);
    }

    @GetMapping(path = {"detail/{id}", "modify/{id}"})
    public ResponseEntity<Object> detail(@PathVariable Long id){
        return giftCardService.getDetail(id);
    }

    @PutMapping("")
    public ResponseEntity<Object> modify(@RequestPart(required = true, value = "giftCardDTO") GiftCardDTO giftCardDTO){
        final ResponseEntity<Object> objectResponseEntity = giftCardService.modifyGiftCard(giftCardDTO);
        return objectResponseEntity;
    }

    @DeleteMapping("")
    public ResponseEntity<Object> delete(@RequestPart List<Long> ids){
        return giftCardService.deleteGiftCards(ids);
    }

}

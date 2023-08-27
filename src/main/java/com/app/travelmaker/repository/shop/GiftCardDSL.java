package com.app.travelmaker.repository.shop;

import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.entity.giftcard.GiftCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GiftCardDSL {
    List<GiftCardDTO> getGiftCardByRegion(String giftCardRegion);

    List<GiftCardDTO> getGiftCardAll();

    public Page<GiftCardDTO> getListWithPage(Pageable pageable);

    public Optional<GiftCardDTO> getDetail(Long id);
}

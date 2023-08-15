package com.app.travelmaker.repository.shop;

import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.entity.giftcard.GiftCard;

import java.util.List;

public interface GiftCardDSL {
    List<GiftCardDTO> getGiftCardByRegion(String giftCardRegion);

    List<GiftCardDTO> getGiftCardAll();
}

package com.app.travelmaker.service.shop;

import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;

import java.util.List;

public interface GiftCardService {
    List<GiftCardDTO> getGiftCardListByGiftCardRegion(String giftCardRegion);

    List<GiftCardDTO> getGiftCardList();

    void addPurchase(PurchaseRequestDTO request);
}

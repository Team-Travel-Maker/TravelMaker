package com.app.travelmaker.service.shop;

import com.app.travelmaker.domain.file.FileSize;
import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.entity.giftcard.GiftCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GiftCardService {
    List<GiftCardDTO> getGiftCardListByGiftCardRegion(String giftCardRegion);

    List<GiftCardDTO> getGiftCardList();

    void addPurchase(PurchaseRequestDTO request);

    public void register(GiftCardDTO giftCardDTO);

    public Page<GiftCardDTO> getListWithPage(Pageable pageable);

    public ResponseEntity<Object> deleteGiftCards(List<Long> ids);

    public ResponseEntity<Object> getDetail(Long id);

    public ResponseEntity<Object> modifyGiftCard(GiftCardDTO giftCardDTO);

    default GiftCard toEntity(GiftCardDTO giftCardDTO){
        return GiftCard.builder()
                .id(giftCardDTO.getId())
                .giftCardTitle(giftCardDTO.getGiftCardTitle())
                .giftCardRegion(giftCardDTO.getGiftCardRegion())
                .giftCardRegionDetail(giftCardDTO.getGiftCardRegionDetail())
                .giftCardPrice(giftCardDTO.getGiftCardPrice())
                .build();
    }
}

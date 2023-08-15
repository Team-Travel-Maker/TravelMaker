package com.app.travelmaker.domain.mypage;

import com.app.travelmaker.domain.shop.GiftCardFileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class MyPageGiftCardDTO {
    private Long id;
    private String giftCardTitle;
    private String giftCardRegion;
    private String giftCardRegionDetail;
    private Integer giftCardPrice;
    private GiftCardFileDTO file;
}

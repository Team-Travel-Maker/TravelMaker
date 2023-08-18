package com.app.travelmaker.domain.mypage.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class MyGiftCardDTO {
    private Long id;
    private String giftCardTitle;
    private String giftCardRegion;
    private String giftCardRegionDetail;
    private Integer payTotalCount;
    private Integer payTotalPrice;
    private String recipientName;
    private LocalDateTime updatedDate;
    private String fileName;
    private String fileUuid;
    private String filePath;
}

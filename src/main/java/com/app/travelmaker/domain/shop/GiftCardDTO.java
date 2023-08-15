package com.app.travelmaker.domain.shop;

import com.app.travelmaker.entity.file.File;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class GiftCardDTO {
    private Long id;
    private String giftCardTitle;
    private String giftCardRegion;
    private String giftCardRegionDetail;
    private Integer giftCardPrice;
    private List<GiftCardFileDTO> files= new ArrayList<>();
}

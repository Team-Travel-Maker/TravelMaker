package com.app.travelmaker.domain.mypage.company;

import com.app.travelmaker.constant.StoreStatus;
import com.app.travelmaker.constant.StoreType;
import com.app.travelmaker.embeddable.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class StoreDTO {
    private Long id;
    private String storeTitle;
    private String storeContent;
    private Address address;
    private StoreType storeType;
    private StoreStatus storeStatus;
    private LocalDateTime updatedDate;
    private List<StoreFileDTO> files = new ArrayList<>();
}

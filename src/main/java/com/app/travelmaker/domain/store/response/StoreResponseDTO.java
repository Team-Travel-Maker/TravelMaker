package com.app.travelmaker.domain.store.response;

import com.app.travelmaker.constant.StoreStatus;
import com.app.travelmaker.constant.StoreType;
import com.app.travelmaker.domain.mypage.company.StoreFileDTO;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.store.StoreFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class StoreResponseDTO {

    private Long id;
    private String storeTitle;
    private String storeContent;
    private String address;
    private String addressDetail;
    private String postCode;
    private StoreType storeType;
    private StoreStatus storeStatus;
    private String storeResult;
    private String memberEmail;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<StoreFileDTO> storeFiles = new ArrayList<>();
}

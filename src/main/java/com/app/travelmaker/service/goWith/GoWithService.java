package com.app.travelmaker.service.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GoWithService {
    //지역 정렬
    public Slice<GoWithDTO> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region);
    //무한 스크롤
    public Slice<GoWithDTO> getListBySlice(Pageable pageable);

    public default GoWithDTO toDTO(GoWith goWith) {
        return GoWithDTO.builder()
                .id(goWith.getId())
                .goWithTitle(goWith.getGoWithTitle())
                .goWithContent(goWith.getGoWithContent())
                .goWithMbti(goWith.getGoWithMbti())
                .goWithRegionType(goWith.getGoWithRegionType())
                .build();
    }

}

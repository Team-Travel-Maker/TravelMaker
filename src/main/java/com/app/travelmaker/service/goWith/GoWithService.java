package com.app.travelmaker.service.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GoWithService {
    //지역 정렬
    public Slice<GoWith> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region);
    //무한 스크롤
    public Slice<GoWithDTO> getListBySlice(Pageable pageable);

    default GoWith toEntity(GoWithDTO goWithDTO) {
        return GoWith.builder().id(goWithDTO.getId())
                .goWithTitle(goWithDTO.getGoWithTitle())
                .goWithContent(goWithDTO.getGoWithContent())
                .goWithMbti(goWithDTO.getGoWithMbti())
                .goWithRegionType(goWithDTO.getGoWithRegionType())
                .build();
    }
    public Page<GoWithDTO> getList(Pageable pageable, GoWithRegionType region);

}

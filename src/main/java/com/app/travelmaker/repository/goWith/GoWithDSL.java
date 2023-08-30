package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GoWithDSL {

//    목록, 무한스크롤
    public Slice<GoWith> findAllWithSliceAndSorting(Pageable pageable, GoWithRegionType region);
    public Page<GoWithDTO> getList(Pageable pageable, GoWithRegionType region);

//    public GoWithDTO getGoWith(Long id);
    public GoWithDTO getGoWith(Long id);


    Long getGoWithsCount(Long memberId);
}

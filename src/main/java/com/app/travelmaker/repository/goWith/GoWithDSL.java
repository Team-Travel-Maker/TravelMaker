package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.domain.gowith.GoWithDTO;

import java.util.List;
import java.util.Optional;

public interface GoWithDSL {

//    목록, 무한스크롤
//    public Slice<GoWith> findAllWithSliceAndSorting(Pageable pageable, GoWithRegionType region);
    public List<GoWithDTO> getPostList(GoWithDTO goWithDTO);

    public Optional<GoWithDTO> detail(Long id);

    public void delete(Long id);



}

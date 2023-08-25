package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.entity.goWith.GoWith;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GoWithDSL {

//    목록, 무한스크롤
    public Slice<GoWith> findAllWithSlice(Pageable pageable);

}

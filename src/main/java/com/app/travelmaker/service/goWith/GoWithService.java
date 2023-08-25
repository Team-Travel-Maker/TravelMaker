package com.app.travelmaker.service.goWith;

import com.app.travelmaker.entity.goWith.GoWith;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GoWithService {
    public Slice<GoWith> getListBySlice(Pageable pageable);
}

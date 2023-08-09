package com.app.travelmaker.repository.cs;

import com.app.travelmaker.domain.cs.CustomServiceResponseDTO;
import com.app.travelmaker.entity.cs.CustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomServiceDSL {

    public Page<CustomService> getListWithPage(Pageable pageable);
}

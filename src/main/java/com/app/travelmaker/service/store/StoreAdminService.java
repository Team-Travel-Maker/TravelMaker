package com.app.travelmaker.service.store;

import com.app.travelmaker.domain.store.response.StoreResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreAdminService {

    public Page<StoreResponseDTO> getList(Pageable pageable);
}

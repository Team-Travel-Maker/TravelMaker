package com.app.travelmaker.service.store;

import com.app.travelmaker.domain.store.response.StoreResponseDTO;
import com.app.travelmaker.entity.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreAdminService {

    public Page<StoreResponseDTO> getList(Pageable pageable);

    public StoreResponseDTO detail(Long id);

    public void modifyStatus(StoreResponseDTO result);



}

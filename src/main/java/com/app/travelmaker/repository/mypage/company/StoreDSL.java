package com.app.travelmaker.repository.mypage.company;

import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.domain.store.response.StoreResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreDSL {
    List<StoreDTO> getAllStore(Long memberId);
    StoreDTO getStore(Long storeId);

    public Page<StoreResponseDTO> getList(Pageable pageable);
}

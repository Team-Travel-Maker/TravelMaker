package com.app.travelmaker.repository.mypage.company;

import com.app.travelmaker.domain.mypage.company.StoreDTO;

import java.util.List;

public interface StoreDSL {
    List<StoreDTO> getAllStore(Long memberId);
    StoreDTO getStore(Long storeId);
}

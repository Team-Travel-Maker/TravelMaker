package com.app.travelmaker.service.store;

import com.app.travelmaker.domain.store.response.StoreResponseDTO;
import com.app.travelmaker.repository.mypage.company.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreAdminServiceImpl implements StoreAdminService {

    private final StoreRepository storeRepository;

    @Override
    public Page<StoreResponseDTO> getList(Pageable pageable) {
        return storeRepository.getList(pageable);
    }

    @Override
    public StoreResponseDTO detail(Long id) {
        return storeRepository.getDetail(id);
    }

    @Override
    public void modifyStatus(StoreResponseDTO result) {
        storeRepository.modifyStatus(result);
    }
}

package com.app.travelmaker.service.mypage.my.customService;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.constant.CsType;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.repository.mypage.my.customService.MyCustomServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyCustomServiceServiceImpl extends AccountSupport implements MyCustomServiceService {

    private final MyCustomServiceRepository myCustomServiceRepository;

    @Override
    public Page<CustomServiceResponseDTO> getMyListFive(Pageable pageable, String keyword) {
        Long memberId = authenticationInfo().getId();
        return myCustomServiceRepository.getMyListFive(pageable, keyword, memberId);
    }

    @Override
    public Page<CustomServiceResponseDTO> getMyListFiveByType(Pageable pageable, String keyword, CsType type) {
        Long memberId = authenticationInfo().getId();
        return myCustomServiceRepository.getMyListFiveByType(pageable, keyword, memberId, type);
    }
}

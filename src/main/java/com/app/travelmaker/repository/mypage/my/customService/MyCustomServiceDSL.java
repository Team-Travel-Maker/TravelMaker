package com.app.travelmaker.repository.mypage.my.customService;

import com.app.travelmaker.constant.CsType;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyCustomServiceDSL {
    public Page<CustomServiceResponseDTO> getMyListFive(Pageable pageable, String keyword, Long memberId);

    Page<CustomServiceResponseDTO> getMyListFiveByType(Pageable pageable, String keyword, Long memberId, CsType type);
}

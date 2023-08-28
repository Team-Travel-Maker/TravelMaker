package com.app.travelmaker.service.mypage.my.customService;

import com.app.travelmaker.constant.CsType;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyCustomServiceService {
    Page<CustomServiceResponseDTO> getMyListFive(Pageable pageable, String keyword);

    Page<CustomServiceResponseDTO> getMyListFiveByType(Pageable pageable, String keyword, CsType type);
}

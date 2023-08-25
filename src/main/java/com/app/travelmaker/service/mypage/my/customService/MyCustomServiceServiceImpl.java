package com.app.travelmaker.service.mypage.my.customService;

import com.app.travelmaker.constant.CsType;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.repository.mypage.my.customService.MyCustomServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MyCustomServiceServiceImpl implements MyCustomServiceService {

    private final MyCustomServiceRepository myCustomServiceRepository;
    private final HttpSession session;

    @Override
    public Page<CustomServiceResponseDTO> getMyListFive(Pageable pageable, String keyword) {
        MemberResponseDTO member = (MemberResponseDTO) session.getAttribute("member");
        return myCustomServiceRepository.getMyListFive(pageable, keyword, member.getId());
    }

    @Override
    public Page<CustomServiceResponseDTO> getMyListFiveByType(Pageable pageable, String keyword, CsType type) {
        MemberResponseDTO member = (MemberResponseDTO) session.getAttribute("member");
        return myCustomServiceRepository.getMyListFiveByType(pageable, keyword, member.getId(), type);
    }
}

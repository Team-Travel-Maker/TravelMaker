package com.app.travelmaker.service.mypage.my.point;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;
import com.app.travelmaker.repository.mypage.my.point.MyPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPointServiceImpl implements MyPointService {

    private final MyPointRepository myPointRepository;
    private final HttpSession session;

    @Override
    public List<MyPointDTO> getMyPoints() {
        final MemberResponseDTO memberDTO = (MemberResponseDTO) session.getAttribute("member");
        return myPointRepository.getMyPoints(memberDTO.getId());
    }

    @Override
    public List<MyPointDTO> getMyPointsByPointType(PointCateGoryType pointType) {
        final MemberResponseDTO memberDTO = (MemberResponseDTO) session.getAttribute("member");
        return myPointRepository.getMyPointsByPointType(memberDTO.getId(), pointType);
    }
}

package com.app.travelmaker.service.mypage.my.point;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;
import com.app.travelmaker.repository.mypage.my.point.MyPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPointServiceImpl extends AccountSupport implements MyPointService {

    private final MyPointRepository myPointRepository;

    @Override
    public List<MyPointDTO> getMyPoints() {
        Long memberId = authenticationInfo().getId();
        return myPointRepository.getMyPoints(memberId);
    }

    @Override
    public List<MyPointDTO> getMyPointsByPointType(PointCateGoryType pointType) {
        Long memberId = authenticationInfo().getId();
        return myPointRepository.getMyPointsByPointType(memberId, pointType);
    }
}

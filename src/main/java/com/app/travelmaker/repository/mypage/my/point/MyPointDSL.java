package com.app.travelmaker.repository.mypage.my.point;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;

import java.util.List;

public interface MyPointDSL {
    List<MyPointDTO> getMyPoints(Long memberId);

    List<MyPointDTO> getMyPointsByPointType(Long memberId, PointCateGoryType pointType);
}

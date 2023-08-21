package com.app.travelmaker.service.mypage.my.point;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;

import java.util.List;

public interface MyPointService {
    public List<MyPointDTO> getMyPoints();

    List<MyPointDTO> getMyPointsByPointType(PointCateGoryType pointType);
}

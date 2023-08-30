package com.app.travelmaker.service.mypage.my.point;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MyPointService {
    public List<MyPointDTO> getMyPoints();
    Page<MyPointDTO> getMyPointsWithPage(Pageable pageable);
    List<MyPointDTO> getMyPointsByPointType(PointCateGoryType pointType);
    Page<MyPointDTO> getMyPointsByPointTypeWithPage(Pageable pageable, PointCateGoryType pointCateGoryType);
}

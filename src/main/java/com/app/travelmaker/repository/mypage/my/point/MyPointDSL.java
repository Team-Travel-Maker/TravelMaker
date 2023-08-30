package com.app.travelmaker.repository.mypage.my.point;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MyPointDSL {
    List<MyPointDTO> getMyPoints(Long memberId);
    Page<MyPointDTO> getMyPointsWithPage(Pageable pageable, Long memberId);
    List<MyPointDTO> getMyPointsByPointType(Long memberId, PointCateGoryType pointType);
    Page<MyPointDTO> getMyPointsByPointTypeWithPage(Pageable pageable, Long memberId, PointCateGoryType pointCateGoryType);
}

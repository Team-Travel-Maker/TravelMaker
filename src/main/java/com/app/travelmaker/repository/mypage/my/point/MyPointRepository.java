package com.app.travelmaker.repository.mypage.my.point;

import com.app.travelmaker.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPointRepository extends JpaRepository<Point, Long>, MyPointDSL {
}

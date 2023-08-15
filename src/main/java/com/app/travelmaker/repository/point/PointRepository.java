package com.app.travelmaker.repository.point;

import com.app.travelmaker.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long>, PointDSL {
}

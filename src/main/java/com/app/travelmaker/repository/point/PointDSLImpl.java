package com.app.travelmaker.repository.point;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PointDSLImpl implements PointDSL{
    @Autowired
    private JPAQueryFactory query;
}

package com.app.travelmaker.repository.cs;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomServiceDSLImpl implements CustomServiceDSL {
    @Autowired
    private JPAQueryFactory query;
}

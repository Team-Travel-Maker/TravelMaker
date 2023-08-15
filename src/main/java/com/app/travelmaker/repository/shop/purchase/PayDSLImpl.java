package com.app.travelmaker.repository.shop.purchase;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PayDSLImpl implements PayDSL{
    @Autowired
    private JPAQueryFactory query;
}

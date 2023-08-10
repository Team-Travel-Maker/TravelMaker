package com.app.travelmaker.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberDSLImpl implements MemberDSL {
    @Autowired
    private JPAQueryFactory query;
}

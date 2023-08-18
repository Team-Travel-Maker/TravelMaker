package com.app.travelmaker.repository.notice;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class NoticeFileDSLImpl implements NoticeFileDSL {

    @Autowired
    private JPAQueryFactory query;
}

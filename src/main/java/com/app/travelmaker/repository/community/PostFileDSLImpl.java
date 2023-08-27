package com.app.travelmaker.repository.community;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PostFileDSLImpl implements PostFileDSL {

    @Autowired
    private JPAQueryFactory query;
}

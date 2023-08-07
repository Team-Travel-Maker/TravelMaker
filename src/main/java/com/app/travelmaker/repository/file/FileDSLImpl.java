package com.app.travelmaker.repository.file;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FileDSLImpl implements FileDSL {
    @Autowired
    private JPAQueryFactory query;
}

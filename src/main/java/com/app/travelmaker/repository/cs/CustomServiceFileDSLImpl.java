package com.app.travelmaker.repository.cs;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomServiceFileDSLImpl implements CustomServiceFileDSL {
    @Autowired
    private JPAQueryFactory query;
}

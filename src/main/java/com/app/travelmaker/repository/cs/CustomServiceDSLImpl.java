package com.app.travelmaker.repository.cs;

import com.app.travelmaker.domain.cs.CustomServiceDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.cs.QCustomService;
import com.app.travelmaker.entity.cs.QCustomServiceFile;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.file.QFile;
import com.app.travelmaker.entity.mebmer.QMember;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.travelmaker.entity.cs.QCustomService.customService;
import static com.app.travelmaker.entity.cs.QCustomServiceFile.customServiceFile;
import static com.app.travelmaker.entity.file.QFile.file;
import static com.app.travelmaker.entity.mebmer.QMember.member;

@Slf4j
public class CustomServiceDSLImpl implements CustomServiceDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Page<CustomService> getListWithPage(Pageable pageable) {
        List<CustomService> customServices = query.selectFrom(customService)
                .orderBy(customService.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(customService.count()).from(customService).fetchOne();

        return new PageImpl<>(customServices, pageable, count);

    }

}

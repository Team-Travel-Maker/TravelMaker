package com.app.travelmaker.repository.cs;

import com.app.travelmaker.domain.cs.CustomServiceDTO;
import com.app.travelmaker.domain.cs.CustomServiceResponseDTO;
import com.app.travelmaker.domain.file.FileDTO;
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
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.cs.QCustomService.customService;
import static com.app.travelmaker.entity.cs.QCustomServiceFile.customServiceFile;
import static com.app.travelmaker.entity.file.QFile.file;
import static com.app.travelmaker.entity.mebmer.QMember.member;

@Slf4j
public class CustomServiceDSLImpl implements CustomServiceDSL {
    @Autowired
    private JPAQueryFactory query;

  /*  @Override
    public Page<CustomService> getListWithPage(Pageable pageable) {
        List<CustomService> customServices = query.selectFrom(customService)
                .orderBy(customService.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(customService.count()).from(customService).fetchOne();

        return new PageImpl<>(customServices, pageable, count);

    }*/
    @Override
    public Page<CustomServiceResponseDTO> getListWithPage(Pageable pageable) {

        final List<FileDTO> files = query.select(Projections.fields(FileDTO.class,
                customServiceFile.id,
                customServiceFile.fileName,
                customServiceFile.filePath,
                customServiceFile.fileSize,
                customServiceFile.fileUuid,
                customServiceFile.fileType
        )).from(customServiceFile).where(customServiceFile.customService.eq(customService)).fetch();


        List<CustomServiceResponseDTO> customServices = query.select(Projections.fields(CustomServiceResponseDTO.class,
                customService.id,
                customService.csTitle,
                customService.csContent,
                customService.csType,
                customService.createdDate,
                customService.updatedDate,
                member.memberName,
                member.memberEmail
        )).from(customService).innerJoin(member).on(member.id.eq(customService.member.id))
                .leftJoin(customServiceFile).on(customServiceFile.customService.eq(customService))
                .orderBy(customService.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().peek(data -> data.setFiles(files)).collect(Collectors.toList());

        Long count = query.select(customService.count()).from(customService).fetchOne();

        return new PageImpl<>(customServices, pageable, count);

    }

}

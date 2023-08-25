package com.app.travelmaker.repository.mypage.my.customService;

import com.app.travelmaker.constant.CsType;
import com.app.travelmaker.domain.cs.response.CsAnswerResponseDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceFileDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.cs.QCsAnswer.csAnswer;
import static com.app.travelmaker.entity.cs.QCustomService.customService;
import static com.app.travelmaker.entity.cs.QCustomServiceFile.customServiceFile;
import static com.app.travelmaker.entity.mebmer.QMember.member;

@Slf4j
public class MyCustomServiceDSLImpl implements MyCustomServiceDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Page<CustomServiceResponseDTO> getMyListFive(Pageable pageable, String keyword, Long memberId) {
        log.info(keyword);

        final List<CustomServiceFileDTO> files = getFiles(memberId);
        final List<CsAnswerResponseDTO> csAnswers = getAnswers(memberId);

        List<CustomServiceResponseDTO> customServices = query.select(Projections.fields(CustomServiceResponseDTO.class,
                customService.id,
                customService.csTitle,
                customService.csContent,
                customService.csType,
                customService.createdDate,
                customService.updatedDate,
                member.memberName,
                member.memberEmail
        )).from(customService).innerJoin(customService.member, member)
                .on(customService.deleted.eq(false))
                .where(customService.member.id.eq(memberId).and(containsKeyword(keyword)))
                .orderBy(customService.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().peek(data -> {
                    if(files != null){
                        files.stream().filter(file -> file.getCustomServiceId().equals(data.getId())).forEach(file-> data.getFiles().add(file));
                    }
                    if(csAnswers != null){
                        csAnswers.stream().filter(answer -> answer.getCustomServiceId().equals(data.getId())).forEach(answer -> data.getCsAnswers().add(answer));
                    }
                }).collect(Collectors.toList());


        Long count = query.select(customService.count()).from(customService).where(customService.deleted.eq(false).and(containsKeyword(keyword))).fetchOne();

        return new PageImpl<>(customServices, pageable, count);
    }

    @Override
    public Page<CustomServiceResponseDTO> getMyListFiveByType(Pageable pageable, String keyword, Long memberId, CsType type) {
        log.info(keyword);

        final List<CustomServiceFileDTO> files = getFiles(memberId);
        final List<CsAnswerResponseDTO> csAnswers = getAnswers(memberId);

        List<CustomServiceResponseDTO> customServices = query.select(Projections.fields(CustomServiceResponseDTO.class,
                customService.id,
                customService.csTitle,
                customService.csContent,
                customService.csType,
                customService.createdDate,
                customService.updatedDate,
                member.memberName,
                member.memberEmail
        )).from(customService).innerJoin(customService.member, member)
                .on(customService.deleted.eq(false))
                .where(customService.member.id.eq(memberId)
                        .and(customService.csType.eq(type))
                        .and(containsKeyword(keyword)))
                .orderBy(customService.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().peek(data -> {
                    if(files != null){
                        files.stream().filter(file -> file.getCustomServiceId().equals(data.getId())).forEach(file-> data.getFiles().add(file));
                    }
                    if(csAnswers != null){
                        csAnswers.stream().filter(answer -> answer.getCustomServiceId().equals(data.getId())).forEach(answer -> data.getCsAnswers().add(answer));
                    }
                }).collect(Collectors.toList());


        Long count = query.select(customService.count())
                .from(customService)
                .where(customService.deleted.eq(false)
                        .and(customService.csType.eq(type))
                        .and(containsKeyword(keyword)))
                .fetchOne();

        return new PageImpl<>(customServices, pageable, count);
    }

    private List<CsAnswerResponseDTO> getAnswers(Long memberId){
        return query.select(Projections.fields(CsAnswerResponseDTO.class,
                csAnswer.id,
                csAnswer.answerContent,
                csAnswer.createdDate,
                csAnswer.updatedDate,
                csAnswer.customService.id.as("customServiceId"),
                csAnswer.deleted
        )).from(csAnswer)
                .where(csAnswer.customService.eq(customService)
                        .and(customService.member.id.eq(memberId))
                        .and(csAnswer.deleted.eq(false)))
                .fetch();
    }

    private List<CustomServiceFileDTO> getFiles(Long memberId){
        return query.select(Projections.fields(CustomServiceFileDTO.class,
                customServiceFile.id,
                customServiceFile.fileName,
                customServiceFile.filePath,
                customServiceFile.fileSize,
                customServiceFile.fileUuid,
                customServiceFile.fileType,
                customServiceFile.customService.id.as("customServiceId")
        )).from(customServiceFile)
                .where(customServiceFile.customService.id.eq(customService.id)
                        .and(customService.member.id.eq(memberId))
                        .and(customServiceFile.deleted.eq(false)))
                .fetch();
    }

    private BooleanExpression containsKeyword(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return null;
        }
        return customService.csTitle.contains(keyword);
    }
}

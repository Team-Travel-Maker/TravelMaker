package com.app.travelmaker.repository.cs;

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

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.cs.QCsAnswer.csAnswer;
import static com.app.travelmaker.entity.cs.QCustomService.customService;
import static com.app.travelmaker.entity.cs.QCustomServiceFile.customServiceFile;
import static com.app.travelmaker.entity.mebmer.QMember.member;

@Slf4j
public class CustomServiceDSLImpl implements CustomServiceDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Page<CustomServiceResponseDTO> getListWithPage(Pageable pageable,String keyword) {

        log.info(keyword);

        final List<CsAnswerResponseDTO> csAnswers = getAnswers();

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
                .where(containsKeyword(keyword))
                .orderBy(customService.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().peek(data -> {
                    if(csAnswers != null){
                        csAnswers.stream().filter(answer -> answer.getCustomServiceId().equals(data.getId())).forEach(answer -> data.getCsAnswers().add(answer));
                    }
                }).collect(Collectors.toList());


        Long count = query.select(customService.count()).from(customService).where(customService.deleted.eq(false).and(containsKeyword(keyword))).fetchOne();

        return new PageImpl<>(customServices, pageable, count);
    }
    @Override
    public Optional<CustomServiceResponseDTO> detail(Long id) {

        final List<CustomServiceFileDTO> files = getFiles();
        final List<CsAnswerResponseDTO> csAnswers = getAnswers();


        Optional<CustomServiceResponseDTO> foundCustomService = Optional.ofNullable(query.select(Projections.fields(CustomServiceResponseDTO.class,
                customService.id,
                customService.csTitle,
                customService.csContent,
                customService.csType,
                customService.createdDate,
                customService.updatedDate,
                member.memberName,
                member.memberEmail
        )).from(customService).innerJoin(customService.member, member)
                .where(customService.deleted.eq(false).and(customService.id.eq(id))).fetchOne());

        foundCustomService.ifPresent((service) -> {
            if(files != null){
                files.stream().filter(file -> file.getCustomServiceId().equals(service.getId())).forEach(file-> service.getFiles().add(file));
            }
            if(csAnswers != null){

                csAnswers.stream().filter(answer -> answer.getCustomServiceId().equals(service.getId())).forEach(answer -> service.getCsAnswers().add(answer));
            }
        });

        return foundCustomService;

    }

    private List<CsAnswerResponseDTO>  getAnswers(){
        return query.select(Projections.fields(CsAnswerResponseDTO.class,
                csAnswer.id,
                csAnswer.answerContent,
                csAnswer.createdDate,
                csAnswer.updatedDate,
                csAnswer.customService.id.as("customServiceId"),
                csAnswer.deleted
        )).from(csAnswer).where(csAnswer.customService.eq(customService).and(csAnswer.deleted.eq(false))).fetch();
    }

    private List<CustomServiceFileDTO> getFiles(){
        return query.select(Projections.fields(CustomServiceFileDTO.class,
                customServiceFile.id,
                customServiceFile.fileName,
                customServiceFile.filePath,
                customServiceFile.fileSize,
                customServiceFile.fileUuid,
                customServiceFile.fileType,
                customServiceFile.customService.id.as("customServiceId")
        )).from(customServiceFile).where(customServiceFile.customService.id.eq(customService.id).and(customServiceFile.deleted.eq(false))).fetch();
    }

    private BooleanExpression containsKeyword(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return null;
        }
        return customService.csTitle.contains(keyword).or(customService.member.memberName.contains(keyword));
    }


}

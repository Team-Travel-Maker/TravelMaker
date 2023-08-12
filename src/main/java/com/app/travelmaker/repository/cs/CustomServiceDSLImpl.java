package com.app.travelmaker.repository.cs;

import com.app.travelmaker.domain.cs.response.CsAnswerResponseDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceFileDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    public Page<CustomServiceResponseDTO> getListWithPage(Pageable pageable) {

        final List<CustomServiceFileDTO> files = query.select(Projections.fields(CustomServiceFileDTO.class,
                customServiceFile.id,
                customServiceFile.fileName,
                customServiceFile.filePath,
                customServiceFile.fileSize,
                customServiceFile.fileUuid,
                customServiceFile.fileType,
                customServiceFile.customService.id.as("customServiceId")
        )).from(customServiceFile).where(customServiceFile.customService.id.eq(customService.id).and(customServiceFile.deleted.eq(false))).fetch();

        log.info(files.toString());

        final List<CsAnswerResponseDTO> csAnswers = query.select(Projections.fields(CsAnswerResponseDTO.class,
                csAnswer.id,
                csAnswer.answerContent,
                csAnswer.createdDate,
                csAnswer.updatedDate,
                csAnswer.customService.id.as("customServiceId"),
                csAnswer.deleted
        )).from(csAnswer).where(csAnswer.customService.eq(customService).and(csAnswer.deleted.eq(false))).fetch();


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
                .where(customService.deleted.eq(false))
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

        log.info(customServices.toString());

        Long count = query.select(customService.count()).from(customService).fetchOne();

        return new PageImpl<>(customServices, pageable, count);


    }
    @Override
    public Optional<CustomServiceResponseDTO> detail(Long id) {

        final List<CustomServiceFileDTO> files = query.select(Projections.fields(CustomServiceFileDTO.class,
                customServiceFile.id,
                customServiceFile.fileName,
                customServiceFile.filePath,
                customServiceFile.fileSize,
                customServiceFile.fileUuid,
                customServiceFile.fileType,
                customServiceFile.customService.id.as("customServiceId")
        )).from(customServiceFile).where(customServiceFile.customService.eq(customService).and(customServiceFile.deleted.eq(false))).fetch();

        final List<CsAnswerResponseDTO> csAnswers = query.select(Projections.fields(CsAnswerResponseDTO.class,
                csAnswer.id,
                csAnswer.answerContent,
                csAnswer.createdDate,
                csAnswer.updatedDate,
                csAnswer.customService.id.as("customServiceId"),
                csAnswer.deleted
        )).from(csAnswer).where(csAnswer.customService.eq(customService).and(csAnswer.deleted.eq(false))).fetch();


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
}

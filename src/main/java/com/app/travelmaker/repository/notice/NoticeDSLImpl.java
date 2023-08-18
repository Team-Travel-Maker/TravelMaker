package com.app.travelmaker.repository.notice;

import com.app.travelmaker.domain.cs.response.CsAnswerResponseDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceFileDTO;
import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.domain.notice.response.NoticeFileResponseDTO;
import com.app.travelmaker.domain.notice.response.NoticeResponseDTO;
import com.app.travelmaker.entity.notice.QNotice;
import com.app.travelmaker.entity.notice.QNoticeFile;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.cs.QCustomService.customService;
import static com.app.travelmaker.entity.cs.QCustomServiceFile.customServiceFile;
import static com.app.travelmaker.entity.mebmer.QMember.member;
import static com.app.travelmaker.entity.notice.QNotice.notice;
import static com.app.travelmaker.entity.notice.QNoticeFile.noticeFile;


public class NoticeDSLImpl implements NoticeDSL {

    @Autowired
    private JPAQueryFactory query;

    @Override
    public Page<NoticeResponseDTO> getListWithPage(Pageable pageable) {


        List<NoticeResponseDTO> notices = query.select(Projections.fields(NoticeResponseDTO.class,
                notice.id,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate,
                notice.updatedDate
        )).from(notice)
                .where(notice.deleted.eq(false))
                .orderBy(notice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(notice.count()).from(notice).fetchOne();

        return new PageImpl<>(notices, pageable, count);
    }

    @Override
    public Optional<NoticeResponseDTO> detail(Long id) {
        final List<NoticeFileResponseDTO> files = getFiles();

        Optional<NoticeResponseDTO> foundNotice = Optional.ofNullable(query.select(Projections.fields(NoticeResponseDTO.class,
                notice.id,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate,
                notice.updatedDate
        )).from(notice)
                .where(notice.deleted.eq(false).and(notice.id.eq(id))).fetchOne());

        foundNotice.ifPresent((service) -> {
            if(files != null){
                files.stream().filter(file -> file.getNoticeId().equals(service.getId())).forEach(file-> service.getFiles().add(file));
            }
        });

        return foundNotice;
    }

    private List<NoticeFileResponseDTO> getFiles() {
        return query.select(Projections.fields(NoticeFileResponseDTO.class,
                noticeFile.id,
                noticeFile.fileName,
                noticeFile.filePath,
                noticeFile.fileSize,
                noticeFile.fileUuid,
                noticeFile.fileType,
                noticeFile.notice.id.as("noticeId")
        )).from(noticeFile).where(noticeFile.notice.id.eq(notice.id).and(noticeFile.deleted.eq(false))).fetch();
    }

}

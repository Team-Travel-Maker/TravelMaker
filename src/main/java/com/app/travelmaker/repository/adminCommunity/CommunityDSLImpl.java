package com.app.travelmaker.repository.adminCommunity;

import com.app.travelmaker.domain.community.CommunityFileResponseDTO;
import com.app.travelmaker.domain.community.CommunityResponseDTO;
import com.app.travelmaker.entity.community.QCommunityFile;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.community.QCommunity.community;
import static com.app.travelmaker.entity.community.QCommunityFile.communityFile;

public class CommunityDSLImpl implements CommunityDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public ResponseEntity<Object> getList(Pageable pageable) {

        List<CommunityFileResponseDTO> files = getFiles();

        List<CommunityResponseDTO> communities =
                query.select(Projections.fields(CommunityResponseDTO.class,
                community.id,
                community.communityTitle,
                community.communityContent,
                community.createdDate,
                community.updatedDate,
                community.communityCategory,
                community.member.memberEmail
        ))
                .from(community)
                .where(community.deleted.eq(false))
                .orderBy(community.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getCommunityId().equals(data.getId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());

        Long count = query.select(community.count()).from(community).where(community.deleted.eq(false)).fetchOne();

        PageImpl<CommunityResponseDTO> communityResponseDTOS = new PageImpl<>(communities, pageable, count);

        return ResponseEntity.status(HttpStatus.OK)
                        .body(communityResponseDTOS);
    }

    private List<CommunityFileResponseDTO> getFiles(){
        return query.select(Projections.fields(CommunityFileResponseDTO.class,
                communityFile.id,
                communityFile.fileName,
                communityFile.filePath,
                communityFile.fileSize,
                communityFile.fileUuid,
                communityFile.fileType,
                communityFile.community.id.as("communityId")
        )).from(communityFile).where(communityFile.community.id.eq(community.id).and(communityFile.deleted.eq(false))).fetch();
    }

}

package com.app.travelmaker.repository.story;

import com.app.travelmaker.domain.cs.response.CustomServiceFileDTO;
import com.app.travelmaker.domain.story.response.StoryFileResponseDTO;
import com.app.travelmaker.domain.story.response.StoryResponseDTO;
import com.app.travelmaker.entity.store.QStore;
import com.app.travelmaker.entity.story.QStory;
import com.app.travelmaker.entity.story.QStoryFile;
import com.app.travelmaker.entity.story.Story;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.cs.QCustomService.customService;
import static com.app.travelmaker.entity.cs.QCustomServiceFile.customServiceFile;
import static com.app.travelmaker.entity.notice.QNotice.notice;
import static com.app.travelmaker.entity.story.QStory.story;
import static com.app.travelmaker.entity.story.QStoryFile.storyFile;


public class StoryDSLImpl implements StoryDSL {

    @Autowired
    private JPAQueryFactory query;


    @Override
    public ResponseEntity<Object> getList(Pageable pageable) {

        List<StoryFileResponseDTO> files = getFiles();

        List<StoryResponseDTO> stories = query.select(Projections.fields(StoryResponseDTO.class,
                story.id,
                story.storyTitle,
                story.storyContent,
                story.createdDate,
                story.updatedDate,
                story.member.memberName,
                story.member.memberEmail
                ))
                .from(story)
                .where(story.deleted.eq(false))
                .orderBy(story.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().peek(data -> {
                    if(files != null){
                        files.stream().filter(file -> file.getStoryId().equals(data.getId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());

        Long count = query.select(story.count()).from(story).where(story.deleted.eq(false)).fetchOne();

        PageImpl<StoryResponseDTO> list = new PageImpl<>(stories, pageable, count);

        return ResponseEntity.status(HttpStatus.OK)
                            .body(list);
    }

    private List<StoryFileResponseDTO> getFiles(){
        return query.select(Projections.fields(StoryFileResponseDTO.class,
                storyFile.id,
                storyFile.fileName,
                storyFile.filePath,
                storyFile.fileSize,
                storyFile.fileUuid,
                storyFile.fileType,
                storyFile.story.id.as("storyId")
        )).from(storyFile).where(storyFile.story.id.eq(story.id).and(storyFile.deleted.eq(false))).fetch();
    }
}

package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyStoryLikeDTO;
import com.app.travelmaker.domain.mypage.my.MyStoryLikeFileDTO;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.story.QStory.story;
import static com.app.travelmaker.entity.story.QStoryFile.storyFile;
import static com.app.travelmaker.entity.story.QStroyLike.stroyLike;

public class MyStoryLikeDSLImpl implements MyStoryLikeDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MyStoryLikeDTO> getStoryLikes(Long memberId) {
        final List<MyStoryLikeFileDTO> files =
                query.select(Projections.fields(MyStoryLikeFileDTO.class,
                        stroyLike.id,
                        storyFile.fileName,
                        storyFile.filePath,
                        storyFile.fileSize,
                        storyFile.fileUuid,
                        storyFile.fileType,
                        storyFile.story.id.as("storyId")
                )).from(stroyLike, storyFile)
                        .where(stroyLike.story.id.eq(storyFile.story.id)
                                .and(stroyLike.member.id.eq(memberId))
                                .and(storyFile.deleted.eq(false))).fetch();

        return query.select(
                Projections.fields(MyStoryLikeDTO.class,
                        stroyLike.id,
                        story.id.as("storyId"),
                        story.storyTitle,
                        story.storyContent,
                        ExpressionUtils.as(
                                JPAExpressions.select(story.count())
                                        .from(stroyLike)
                                        .where(stroyLike.story.id.eq(story.id)),
                                "storyLikeCount"),
                        story.member.memberName,
                        story.updatedDate
                )
        ).from(stroyLike)
                .join(story)
                .on(stroyLike.story.id.eq(story.id))
                .where((stroyLike.member.id.eq(memberId))
                        .and(stroyLike.deleted.eq(false))
                )
                .orderBy(stroyLike.id.desc())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getStoryId().equals(data.getStoryId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public Long getStoryLikesCount(Long memberId) {
        return query.select(stroyLike.count())
                .from(stroyLike)
                .where(stroyLike.member.id.eq(memberId)
                        .and(stroyLike.deleted.eq(false)))
                .fetchOne();
    }

    @Override
    public List<MyStoryLikeDTO> getStoryLikesMax3(Long memberId) {
        final List<MyStoryLikeFileDTO> files =
                query.select(Projections.fields(MyStoryLikeFileDTO.class,
                        stroyLike.id,
                        storyFile.fileName,
                        storyFile.filePath,
                        storyFile.fileSize,
                        storyFile.fileUuid,
                        storyFile.fileType,
                        storyFile.story.id.as("storyId")
                )).from(stroyLike, storyFile)
                        .where(stroyLike.story.id.eq(storyFile.story.id)
                                .and(stroyLike.member.id.eq(memberId))
                                .and(storyFile.deleted.eq(false))).fetch();

        return query.select(
                Projections.fields(MyStoryLikeDTO.class,
                        stroyLike.id,
                        story.id.as("storyId"),
                        story.storyTitle,
                        story.storyContent,
                        ExpressionUtils.as(
                                JPAExpressions.select(story.count())
                                        .from(stroyLike)
                                        .where(stroyLike.story.id.eq(story.id)),
                                "storyLikeCount"),
                        story.member.memberName,
                        story.updatedDate
                )
        ).from(stroyLike)
                .join(story)
                .on(stroyLike.story.id.eq(story.id))
                .where((stroyLike.member.id.eq(memberId))
                        .and(stroyLike.deleted.eq(false))
                )
                .orderBy(stroyLike.id.desc())
                .limit(3)
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getStoryId().equals(data.getStoryId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }
}

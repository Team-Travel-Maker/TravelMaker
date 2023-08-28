package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;
import com.app.travelmaker.domain.mypage.my.MyBookmarkFileDTO;
import com.app.travelmaker.entity.theme.QThemeFile;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.store.QStore.store;
import static com.app.travelmaker.entity.theme.QTheme.theme;
import static com.app.travelmaker.entity.theme.QThemeBookMark.themeBookMark;
import static com.app.travelmaker.entity.theme.QThemeFile.themeFile;

public class MyBookmarkDSLImpl implements MyBookmarkDSL{
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MyBookmarkDTO> getBookMarks(Long memberId) {
        final List<MyBookmarkFileDTO> files =
                query.select(Projections.fields(MyBookmarkFileDTO.class,
                        themeFile.id,
                        themeFile.fileName,
                        themeFile.filePath,
                        themeFile.fileSize,
                        themeFile.fileUuid,
                        themeFile.fileType,
                        themeFile.theme.id.as("bookmarkId")
                )).from(themeBookMark, themeFile)
                        .where(themeBookMark.theme.id.eq(themeFile.theme.id)
                                .and(themeBookMark.member.id.eq(memberId))
                                .and(themeFile.deleted.eq(false))).fetch();

        return query.select(
                Projections.fields(MyBookmarkDTO.class,
                        themeBookMark.id,
                        theme.id.as("themeId"),
                        theme.themeTitle,
                        theme.themeContent,
                        theme.themeStartDate,
                        theme.themeEndDate
                )
        ).from(themeBookMark)
                .join(theme)
                .on(themeBookMark.theme.id.eq(theme.id))
                .where((themeBookMark.member.id.eq(memberId))
                        .and(themeBookMark.deleted.eq(false))
                )
                .orderBy(themeBookMark.id.desc())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getBookmarkId().equals(data.getThemeId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public Long getBookMarksCount(Long memberId) {
        return query.select(themeBookMark.count())
                .from(themeBookMark)
                .where(themeBookMark.member.id.eq(memberId)
                        .and(themeBookMark.deleted.eq(false)))
                .fetchOne();
    }

    @Override
    public List<MyBookmarkDTO> getBookMarksMax4(Long memberId) {
        final List<MyBookmarkFileDTO> files =
                query.select(Projections.fields(MyBookmarkFileDTO.class,
                        themeFile.id,
                        themeFile.fileName,
                        themeFile.filePath,
                        themeFile.fileSize,
                        themeFile.fileUuid,
                        themeFile.fileType,
                        themeFile.theme.id.as("bookmarkId")
                )).from(themeBookMark, themeFile)
                        .where(themeBookMark.theme.id.eq(themeFile.theme.id)
                                .and(themeBookMark.member.id.eq(memberId))
                                .and(themeFile.deleted.eq(false))).fetch();

        return query.select(
                Projections.fields(MyBookmarkDTO.class,
                        themeBookMark.id,
                        theme.id.as("themeId"),
                        theme.themeTitle,
                        theme.themeContent,
                        theme.themeStartDate,
                        theme.themeEndDate
                )
        ).from(themeBookMark)
                .join(theme)
                .on(themeBookMark.theme.id.eq(theme.id))
                .where((themeBookMark.member.id.eq(memberId))
                        .and(themeBookMark.deleted.eq(false))
                )
                .orderBy(themeBookMark.id.desc())
                .limit(4)
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getBookmarkId().equals(data.getThemeId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }
}

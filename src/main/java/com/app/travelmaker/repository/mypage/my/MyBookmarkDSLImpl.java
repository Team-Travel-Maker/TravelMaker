package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;
import com.app.travelmaker.domain.mypage.my.MyBookmarkFileDTO;
import com.app.travelmaker.entity.theme.QThemeFile;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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
                        themeBookMark.id,
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
                        theme.id,
                        theme.themeTitle,
                        theme.themeContent,
                        theme.themeStartDate,
                        theme.themeEndDate
                        )
        ).from(themeBookMark, theme)
                .where(themeBookMark.theme.id.eq(theme.id)
                        .and(themeBookMark.member.id.eq(memberId))
                        .and(themeBookMark.deleted.eq(false))
                )
                .orderBy(themeBookMark.id.desc())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getBookmarkId().equals(data.getId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }
}

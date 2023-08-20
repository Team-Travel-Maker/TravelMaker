package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;
import com.app.travelmaker.domain.mypage.my.MyBookmarkFileDTO;
import com.app.travelmaker.domain.mypage.my.MyCommunityLikeDTO;
import com.app.travelmaker.domain.mypage.my.MyCommunityLikeFileDTO;
import com.app.travelmaker.entity.community.QCommunity;
import com.app.travelmaker.entity.community.QCommunityLike;
import com.app.travelmaker.entity.community.QCommuntiyFile;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.community.QCommunity.community;
import static com.app.travelmaker.entity.community.QCommunityLike.communityLike;
import static com.app.travelmaker.entity.community.QCommuntiyFile.communtiyFile;

public class MyCommunityLikeDSLImpl implements MyCommunityLikeDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MyCommunityLikeDTO> getCommunityLikes(Long memberId) {
        final List<MyCommunityLikeFileDTO> files =
                query.select(Projections.fields(MyCommunityLikeFileDTO.class,
                        communityLike.id,
                        communtiyFile.fileName,
                        communtiyFile.filePath,
                        communtiyFile.fileSize,
                        communtiyFile.fileUuid,
                        communtiyFile.fileType,
                        communtiyFile.community.id.as("communityId")
                )).from(communityLike, communtiyFile)
                        .where(communityLike.community.id.eq(communtiyFile.community.id)
                                .and(communityLike.member.id.eq(memberId))
                                .and(communtiyFile.deleted.eq(false))).fetch();

        return query.select(
                Projections.fields(MyCommunityLikeDTO.class,
                        communityLike.id,
                        community.id.as("communityId"),
                        community.communityTitle,
                        community.communityContent,
                        community.communityCategory,
                        ExpressionUtils.as(
                                JPAExpressions.select(community.count())
                                        .from(communityLike)
                                        .where(communityLike.community.id.eq(community.id)),
                                "communityLikeCount"),
                        community.member.memberName,
                        community.updatedDate
                )
        ).from(communityLike)
                .join(community)
                .on(communityLike.community.id.eq(community.id))
                .where((communityLike.member.id.eq(memberId))
                        .and(communityLike.deleted.eq(false))
                )
                .orderBy(communityLike.id.desc())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getCommunityId().equals(data.getCommunityId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }
}
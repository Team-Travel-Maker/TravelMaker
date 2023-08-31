package com.app.travelmaker.repository.community;

import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.entity.mebmer.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.app.travelmaker.entity.community.QCommunity.community;
import static com.app.travelmaker.entity.community.QCommunityLike.communityLike;
import static com.app.travelmaker.entity.mebmer.QMember.member;

public class PostDSLImpl implements PostDSL {

    @Autowired
    private JPAQueryFactory query;


    @Override
    public List<PostDTO> getPostList(PostDTO postDTO) {
        query.select().from().where().fetch();

        return null;
    }

    @Override
    public Optional<PostDTO> detail(Long id) {

        Optional<PostDTO> foundPost = Optional.ofNullable(query.select(Projections.constructor(PostDTO.class,
                community.id,
                community.communityTitle,
                community.communityContent,
                community.communityCategory,
                community.createdDate,
                community.member
                )).from(community).innerJoin(community.member, member)
                .on(community.member.id.eq(member.id))
                .where(community.id.eq(id))
                .fetchOne());



        return foundPost;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long getCommunitiesCount(Long memberId) {
        return query.select(community.count())
                .from(community)
                .where(community.member.id.eq(memberId)
                        .and(community.deleted.eq(false)))
                .fetchOne();
    }
}

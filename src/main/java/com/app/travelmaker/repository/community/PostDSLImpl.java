package com.app.travelmaker.repository.community;

import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.entity.community.CommunityReply;
import com.app.travelmaker.entity.community.CommunityTag;
import com.app.travelmaker.entity.community.QCommunityReply;
import com.app.travelmaker.entity.community.QCommunityTag;
import com.app.travelmaker.entity.mebmer.QMember;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.community.QCommunity.community;
import static com.app.travelmaker.entity.community.QCommunityLike.communityLike;
import static com.app.travelmaker.entity.community.QCommunityReply.communityReply;
import static com.app.travelmaker.entity.mebmer.QMember.member;

@Slf4j
public class PostDSLImpl implements PostDSL {

    @Autowired
    private JPAQueryFactory query;


    @Override
    public List<PostDTO> getPostList(CommunityType communityType) {
//      처음 글 목록페이지 이동 했을 때 기본적으로 보이는
//      게시글 타입을 REVIEW 로 설정
        System.out.println("글 목록 DSLImpl");
        System.out.println("PostDSLImpl : " + communityType);
        if(communityType == null){
            communityType = CommunityType.REVIEW;
        }

        List postList =
                query.select(Projections.fields(PostDTO.class,
                        community.id,
                        community.communityTitle.as("postTitle"),
                        community.communityContent.as("postContent"),
                        community.communityCategory.as("communityType"),
                        community.createdDate.as("createTime"),
                        community.updatedDate,
                        community.member,
                        community.member.memberName.as("memberName")
                        ))
                        .from(community)
                        .innerJoin(community.member, member)
                        .where(community.communityCategory.eq(communityType)
                                .and(community.deleted.eq(false)))
                        .orderBy(community.createdDate.desc())
                        .fetch();
//                        .stream()
//                        .peek(data -> {
//                            long replyCount = query.select(communityReply.count())
//                                    .from(communityReply)
//                                    .where(communityReply.id.eq(data.getId()))
//                                    .fetchOne();
//                            data.setReplyCount(replyCount);
//                        }).collect(Collectors.toList());

        return postList;
    }

    @Override
    public Optional<PostDTO> detail(Long id) {
        System.out.println("디테일 DSLImpl id : " + id);

        Optional<PostDTO> foundPost =
                Optional.ofNullable(
                        query.select(Projections.constructor(PostDTO.class,
                        community.id,
                        community.communityTitle.as("postTitle"),
                        community.communityContent.as("postContent"),
                        community.communityCategory.as("communityType"),
                        community.createdDate.as("createTime"),
                        community.member
                )).from(community).innerJoin(community.member, member)
                    .on(community.deleted.eq(false))
                    .where(community.id.eq(id))
                    .fetchOne());


        log.info("=D=S=L=[]=D=S=L=", foundPost);
        return foundPost;
    }




    @Override
    public void modifyPost(PostDTO postDTO) {
        query.update(community)
                .set(
                      community.communityTitle,
                      community.communityContent
//                      community.communityCategory,
//                      community.createdDate,
//                      community.member
                    )
                .where(community.id.eq(postDTO.getId())).execute();
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

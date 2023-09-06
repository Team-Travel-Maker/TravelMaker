package com.app.travelmaker.domain.community;


import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.tag.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data @ToString
@NoArgsConstructor
@Slf4j
public class PostDTO {

    private Long id;
    private String postTitle;
    private String postContent;
    private Long postLike;
    private Long replyCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private CommunityType communityType;
    private Member member;
    private String memberName;
    private Tag tag;

    private List<FileDTO> files = new ArrayList<>();
    private List<Long> fileIdsForDelete = new ArrayList<>();


    public PostDTO(Long id, String postTitle, String postContent, CommunityType communityType, LocalDateTime createTime, Member member) {
        this.id = id;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.communityType = communityType;
        this.createTime = createTime;
        this.member = member;
    }

    public PostDTO(Long id, String postTitle, String postContent, Long postLike, Long replyCount, LocalDateTime createTime, CommunityType communityType, Member member, Tag tag) {
        this.id = id;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postLike = postLike;
        this.replyCount = replyCount;
        this.createTime = createTime;
        this.communityType = communityType;
        this.member = member;
        this.tag = tag;
    }

    public PostDTO(Long id, String postTitle, String postContent, Long postLike, Long replyCount, LocalDateTime createTime, CommunityType communityType, String memberName) {
        this.id = id;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.communityType = communityType;
        this.createTime = createTime;
        this.postLike = postLike;
        this.replyCount = replyCount;
        this.memberName = memberName;
    }
}

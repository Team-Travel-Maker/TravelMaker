package com.app.travelmaker.service.community;


import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.entity.community.Community;
import com.app.travelmaker.service.MemberSupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface CommunityService extends MemberSupport {

    public Long write(PostDTO postDTO);

    public List<PostDTO> getPostList(CommunityType communityType);

    public PostDTO detail(Long id);

    public void postDelete(Long id);

    public void modifyPost(PostDTO postDTO);





    default Community toEntity(PostDTO postDTO){
        return Community.builder()
                .id(postDTO.getId())
                .communityTitle(postDTO.getPostTitle())
                .communityContent(postDTO.getPostContent())
                .communityCategory(postDTO.getCommunityType())
                .createdDate(LocalDateTime.now())
                .tags(postDTO.getTag())
                .member(postDTO.getMember())
                .build();
    }







}

package com.app.travelmaker.service.community;


import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.entity.community.Community;

import java.util.List;



public interface CommunityService {

    public List<PostDTO> getPostList(PostDTO postDTO);

    public void write(PostDTO postDTO);

    public PostDTO postDetail(Long id);

    public void postDelete(Long id);



    default Community toEntity(PostDTO postDTO){
        return Community.builder()
                .id(postDTO.getId())
                .communityTitle(postDTO.getPostTitle())
                .communityContent(postDTO.getPostContent())
                .communityCategory(postDTO.getCommunityType())
                .createdDate(postDTO.getCreateTime())
                .build();
    }


}

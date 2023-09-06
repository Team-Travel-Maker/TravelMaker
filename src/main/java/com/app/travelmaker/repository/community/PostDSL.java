package com.app.travelmaker.repository.community;

import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.community.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostDSL {

    public List<PostDTO> getPostList(CommunityType communityType);

    public Optional<PostDTO> detail(Long id);

    public void delete(Long id);

    Long getCommunitiesCount(Long memberId);
}

package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyCommunityLikeDTO;

import java.util.List;

public interface MyCommunityLikeService {
    List<MyCommunityLikeDTO> getCommunityLikes();

    void deleteCommunityLike(Long communityLikeId);
}

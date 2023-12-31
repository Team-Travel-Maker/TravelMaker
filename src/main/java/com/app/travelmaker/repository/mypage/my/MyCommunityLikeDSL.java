package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyCommunityLikeDTO;

import java.util.List;

public interface MyCommunityLikeDSL {
    List<MyCommunityLikeDTO> getCommunityLikes(Long memberId);

    Long getCommunityLikesCount(Long memberId);

    List<MyCommunityLikeDTO> getCommunityLikesMax3(Long memberId);
}

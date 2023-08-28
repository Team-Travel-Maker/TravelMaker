package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyCommunityLikeDTO;
import com.app.travelmaker.domain.mypage.my.MyStoryLikeDTO;

import java.util.List;

public interface MyStoryLikeDSL {
    List<MyStoryLikeDTO> getStoryLikes(Long memberId);

    Long getStoryLikesCount(Long memberId);

    List<MyStoryLikeDTO> getStoryLikesMax3(Long memberId);
}

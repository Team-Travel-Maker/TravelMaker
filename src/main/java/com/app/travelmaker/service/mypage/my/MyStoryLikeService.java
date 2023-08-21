package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyStoryLikeDTO;

import java.util.List;

public interface MyStoryLikeService {
    List<MyStoryLikeDTO> getStoryLikes();

    void deleteStoryLike(Long storyLikeId);
}

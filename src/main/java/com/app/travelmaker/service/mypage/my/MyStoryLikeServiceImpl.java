package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.common.CommonSupport;
import com.app.travelmaker.domain.mypage.my.MyStoryLikeDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.mypage.my.MyStoryLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyStoryLikeServiceImpl extends AccountSupport implements MyStoryLikeService {
    private final MyStoryLikeRepository myStoryLikeRepository;

    @Override
    public List<MyStoryLikeDTO> getStoryLikes() {
        Long memberId = authenticationInfo().getId();
        return myStoryLikeRepository.getStoryLikes(memberId);
    }

    @Override
    public void deleteStoryLike(Long storyLikeId) {
        myStoryLikeRepository.deleteById(storyLikeId);
    }
}

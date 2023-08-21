package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.common.CommonSupport;
import com.app.travelmaker.domain.mypage.my.MyCommunityLikeDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.mypage.my.MyCommunityLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyCommunityLikeServiceImpl extends CommonSupport implements MyCommunityLikeService {
    private final MyCommunityLikeRepository myCommunityLikeRepository;

    @Override
    public List<MyCommunityLikeDTO> getCommunityLikes() {
        Member member = authenticationInfo();
        return myCommunityLikeRepository.getCommunityLikes(member.getId());
    }

    @Override
    public void deleteCommunityLike(Long communityLikeId) {
        myCommunityLikeRepository.deleteById(communityLikeId);
    }
}

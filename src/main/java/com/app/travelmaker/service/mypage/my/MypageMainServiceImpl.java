package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.domain.mypage.my.*;
import com.app.travelmaker.repository.community.PostRepository;
import com.app.travelmaker.repository.eco.EcoRepository;
import com.app.travelmaker.repository.goWith.GoWithRepository;
import com.app.travelmaker.repository.mypage.company.StoreRepository;
import com.app.travelmaker.repository.mypage.my.MyBookmarkRepository;
import com.app.travelmaker.repository.mypage.my.MyCommunityLikeRepository;
import com.app.travelmaker.repository.mypage.my.MyGiftCardRepository;
import com.app.travelmaker.repository.mypage.my.MyStoryLikeRepository;
import com.app.travelmaker.repository.mypage.my.customService.MyCustomServiceRepository;
import com.app.travelmaker.repository.story.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageMainServiceImpl extends AccountSupport implements MypageMainService {
    private final StoreRepository storeRepository;
    private final MyCustomServiceRepository myCustomServiceRepository;
    private final MyGiftCardRepository myGiftCardRepository;
    private final MyBookmarkRepository myBookmarkRepository;
    private final MyCommunityLikeRepository myCommunityLikeRepository;
    private final MyStoryLikeRepository myStoryLikeRepository;
    private final StoryRepository storyRepository;
    private final PostRepository postRepository;
    private final EcoRepository ecoRepository;
    private final GoWithRepository goWithRepository;

    @Override
    public Long getCompanyCount() {
        return storeRepository.getCompanyCount(authenticationInfo().getId());
    }

    @Override
    public Long getCustomServiceCount() {
        return myCustomServiceRepository.getCustomServiceCount(authenticationInfo().getId());
    }

    @Override
    public Long getGiftCardCount() {
        return myGiftCardRepository.getGiftCardCount(authenticationInfo().getId());
    }

    @Override
    public Long getStoriesCount() {
        return storyRepository.getStoriesCount(authenticationInfo().getId());
    }

    @Override
    public Long getCommunitiesCount() {
        return postRepository.getCommunitiesCount(authenticationInfo().getId());
    }

    @Override
    public Long getEcosCount() {
        return ecoRepository.getEcosCount(authenticationInfo().getId());
    }

    @Override
    public Long getGoWithsCount() {
        return goWithRepository.getGoWithsCount(authenticationInfo().getId());
    }

    @Override
    public Long getBookmarksCount() {
        return myBookmarkRepository.getBookMarksCount(authenticationInfo().getId());
    }

    @Override
    public Long getLikesCount() {
        Long myCummunityLikesCount = myCommunityLikeRepository.getCommunityLikesCount(authenticationInfo().getId());
        Long myStoryLikesCount = myStoryLikeRepository.getStoryLikesCount(authenticationInfo().getId());
        return myCummunityLikesCount + myStoryLikesCount;
    }

    @Override
    public List<MyBookmarkDTO> getBookmarksMax4() {
        return myBookmarkRepository.getBookMarksMax4(authenticationInfo().getId());
    }

    @Override
    public List<MyLikeDTO> getLikesMax6() {
        List<MyLikeDTO> myLikeDTOS = new ArrayList<>();
        List<MyCommunityLikeDTO> communityLikesMax3 = myCommunityLikeRepository.getCommunityLikesMax3(authenticationInfo().getId());
        List<MyStoryLikeDTO> storyLikesMax3 = myStoryLikeRepository.getStoryLikesMax3(authenticationInfo().getId());
        communityLikesMax3.forEach(myCommunityLikeDTO -> {
            myLikeDTOS.add(MyLikeDTO.builder()
                    .id(myCommunityLikeDTO.getId())
                    .communityId(myCommunityLikeDTO.getCommunityId())
                    .likeTitle(myCommunityLikeDTO.getCommunityTitle())
                    .likeContent(myCommunityLikeDTO.getCommunityContent())
                    .likeName(myCommunityLikeDTO.getMemberName())
                    .likeType("COMMUNITY")
                    .files(communityToMy(myCommunityLikeDTO.getFiles()))
                    .build());
        });
        storyLikesMax3.forEach(myStoryLikeDTO -> {
            myLikeDTOS.add(MyLikeDTO.builder()
                    .id(myStoryLikeDTO.getId())
                    .storyId(myStoryLikeDTO.getStoryId())
                    .likeTitle(myStoryLikeDTO.getStoryTitle())
                    .likeContent(myStoryLikeDTO.getStoryContent())
                    .likeName(myStoryLikeDTO.getMemberName())
                    .likeType("STORY")
                    .files(storyToMy(myStoryLikeDTO.getFiles()))
                    .build());
        });
        return myLikeDTOS;
    }
}

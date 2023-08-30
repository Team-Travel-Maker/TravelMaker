package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.domain.mypage.my.*;

import java.util.List;
import java.util.stream.Collectors;

public interface MypageMainService {
    Long getCompanyCount();

    Long getCustomServiceCount();

    Long getGiftCardCount();

    Long getStoriesCount();

    Long getCommunitiesCount();

    Long getEcosCount();

    Long getGoWithsCount();
    Long getBookmarksCount();

    Long getLikesCount();

    List<MyBookmarkDTO> getBookmarksMax4();

    List<MyLikeDTO> getLikesMax6();

    default List<MyLikeFileDTO> communityToMy(List<MyCommunityLikeFileDTO> myCommunityLikeFileDTOS) {
        return myCommunityLikeFileDTOS.stream().map(myCommunityLikeFileDTO -> MyLikeFileDTO.builder()
                .id(myCommunityLikeFileDTO.getId())
                .fileName(myCommunityLikeFileDTO.getFileName())
                .filePath(myCommunityLikeFileDTO.getFilePath())
                .fileSize(myCommunityLikeFileDTO.getFileSize())
                .fileType(myCommunityLikeFileDTO.getFileType())
                .fileUuid(myCommunityLikeFileDTO.getFileUuid())
                .likeId(myCommunityLikeFileDTO.getCommunityId())
                .build()).collect(Collectors.toList());
    }

    default List<MyLikeFileDTO> storyToMy(List<MyStoryLikeFileDTO> myStoryLikeFileDTOS) {
        return myStoryLikeFileDTOS.stream().map(myStoryLikeFileDTO -> MyLikeFileDTO.builder()
                .id(myStoryLikeFileDTO.getId())
                .fileName(myStoryLikeFileDTO.getFileName())
                .filePath(myStoryLikeFileDTO.getFilePath())
                .fileSize(myStoryLikeFileDTO.getFileSize())
                .fileType(myStoryLikeFileDTO.getFileType())
                .fileUuid(myStoryLikeFileDTO.getFileUuid())
                .likeId(myStoryLikeFileDTO.getStoryId())
                .build()).collect(Collectors.toList());
    }
}

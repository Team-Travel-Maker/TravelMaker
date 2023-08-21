package com.app.travelmaker.domain.mypage.my;

import com.app.travelmaker.constant.CommunityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class MyStoryLikeDTO {
    private Long id;
    private Long storyId;
    private String storyTitle;
    private String storyContent;
    // 각 스토리 별 좋아요 개수
    private Long storyLikeCount;
    // 올린 사람 이름
    private String memberName;
    private LocalDateTime updatedDate;
    private List<MyStoryLikeFileDTO> files= new ArrayList<>();
}

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
public class MyCommunityLikeDTO {
    private Long id;
    private Long communityId;
    private String communityTitle;
    private String communityContent;
    private CommunityType communityCategory;
    // 각 커뮤니티 별 좋아요 개수
    private Long communityLikeCount;
    // 올린 사람 이름
    private String memberName;
    private LocalDateTime updatedDate;
    private List<MyCommunityLikeFileDTO> files= new ArrayList<>();
}

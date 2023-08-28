package com.app.travelmaker.domain.mypage.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class MyLikeDTO {
    private Long id;
    private Long storyId;
    private Long communityId;
    private String likeTitle;
    private String likeContent;
    // 올린 사람 이름
    private String likeName;
    private String likeType;
    private List<MyLikeFileDTO> files= new ArrayList<>();
}

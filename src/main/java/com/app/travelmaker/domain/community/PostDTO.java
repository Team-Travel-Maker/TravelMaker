package com.app.travelmaker.domain.community;


import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.file.FileDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data @ToString
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String postTitle;
    private String postContent;
    private Long postLike;
    private Long replyCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long memberId;
    private String memberName;
    private CommunityType communityType;

    private List<FileDTO> files = new ArrayList<>();
    private List<Long> fileIdsForDelete = new ArrayList<>();




}

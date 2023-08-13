package com.app.travelmaker.domain.community;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String postTitle;
    private String postContent;
    private Long postLike;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long memberId;
    private Long replyCount;
    private String memberName;




}

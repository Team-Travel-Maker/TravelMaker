package com.app.travelmaker.domain.gowith;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GoWithReplyDTO {

    private Long id;
    private String replyContent;
    private Long replyGroup;
    private Long replyDepth;
    private boolean deleted;


    public GoWithReplyDTO(Long id, String replyContent, Long replyGroup, Long replyDepth, boolean deleted) {
        this.id = id;
        this.replyContent = replyContent;
        this.replyGroup = replyGroup;
        this.replyDepth = replyDepth;
        this.deleted = deleted;
    }
}

package com.app.travelmaker.domain.gowith;

import com.app.travelmaker.entity.mebmer.Member;
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
    private Member member;  // Member 정보 추가


    public GoWithReplyDTO(Long id, String replyContent, Long replyGroup, Long replyDepth,Member member, boolean deleted) {
        this.id = id;
        this.replyContent = replyContent;
        this.replyGroup = replyGroup;
        this.replyDepth = replyDepth;
        this.member = member;
        this.deleted = deleted;
    }
}

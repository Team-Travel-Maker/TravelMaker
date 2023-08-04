package com.app.travelmaker.entity.story;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.reply.Reply;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * StoryReply Entity (스토리 댓글 중간 테이블)
 * */

@Entity
@Table(name = "TBL_STORY_REPLY")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_STORY_REPLY SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class StoryReply extends Reply {

    /**
     * Story (스토리와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;

    /**
     * StoryReply Status (스토리 댓글 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}

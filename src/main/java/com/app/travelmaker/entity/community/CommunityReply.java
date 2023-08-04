package com.app.travelmaker.entity.community;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.reply.Reply;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * CommunityReply Entity (커뮤니티 댓글 중간 테이블)
 * */

@Entity
@Table(name = "TBL_COMMUNITY_REPLY")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_COMMUNITY_REPLY SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class CommunityReply extends Reply {

    /**
     * Community (Community 와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;

    /**
     * CommunityReply Status (커뮤니티 댓글 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

}

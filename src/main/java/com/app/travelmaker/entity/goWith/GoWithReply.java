package com.app.travelmaker.entity.goWith;

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
 * GoWithReply Entity (같이 가요 댓글 중간 테이블)
 * */

@Entity
@Table(name = "TBL_GOWITH_REPLY")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_GOWITH_REPLY SET DELETED = 1 WHERE ID = ?")
public class GoWithReply extends Reply {

    /**
     * GoWith (같이 가요와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private GoWith goWith;

    /**
     * GoWithReply Status (같이 가요 댓글 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}

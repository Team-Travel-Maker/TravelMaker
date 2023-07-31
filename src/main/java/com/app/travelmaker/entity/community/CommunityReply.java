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
public class CommunityReply extends Period {

    /**
     * CommunityReply PK(커뮤니티 댓글 중간 테이블 고유 번호)
     * */
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * CommunityReply FK(슈퍼키 서브키)
     * PK 이자 FK 연결 Reply의 PK 와 연결됌 (N : 1)
     * */
    @MapsId
    @JoinColumn(name = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reply reply;



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

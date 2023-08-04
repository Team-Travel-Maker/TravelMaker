package com.app.travelmaker.entity.reply;

import com.app.travelmaker.auditing.Period;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 * Reply Entity (댓글)
 * */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_REPLY")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_REPLY SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Reply extends Period {

    /**
     * Reply PK (댓글 고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Reply Content (댓글 내용)
     * */
    @NotNull private String replyContent;

    /**
     * Reply Group (댓글 소속)
     * */
    @NotNull private Long replyGroup;

    /**
     * Reply depth (댓글/ 대댓글 구분 depth)
     * */
    @NotNull private Long replyDepth;

    /**
     * Reply Status (댓글 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}

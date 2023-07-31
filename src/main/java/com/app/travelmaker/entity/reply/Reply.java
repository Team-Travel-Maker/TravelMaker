package com.app.travelmaker.entity.reply;

import com.app.travelmaker.auditing.Period;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * Reply Entity (댓글)
 * */

@Entity
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
    @NotNull private String content;

    /**
     * Reply Group (댓글 소속)
     * */
    @NotNull private Long group;

    /**
     * Reply depth (댓글/ 대댓글 구분 depth)
     * */
    @NotNull private Long depth;

    /**
     * Reply Status (댓글 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}

package com.app.travelmaker.entity.tag;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.SpringApplicationExtensionsKt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Tag Entity (댓글)
 * */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_TAG")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_TAG SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Tag {

    /**
     * Tag PK (태그 고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Tag Content (태그 내용)
     * */
    @NotNull private String content;

    /**
     * Tag Status (태그 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}

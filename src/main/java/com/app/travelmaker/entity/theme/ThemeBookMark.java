package com.app.travelmaker.entity.theme;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Theme Book Mark Entity (테마 북마크)
 * */

@Entity
@Table(name = "TBL_THEME_BOOKMARK")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_THEME_BOOKMARK SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class ThemeBookMark extends Period {
    /**
     * Theme Book Mark PK (테마 고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Member Entity 와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    /**
     * Theme Entity 와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Theme theme;

    /**
     * ThemeT Book Mark Status (테마 북마크 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

}

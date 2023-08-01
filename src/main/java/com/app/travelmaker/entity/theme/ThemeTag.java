package com.app.travelmaker.entity.theme;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.tag.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * ThemeTag Entity (테마 태그 중간 테이블)
 * */

@Entity
@Table(name = "TBL_THEME_TAG")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_THEME_TAG SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class ThemeTag extends Period {

    /**
     * ThemeTag PK (테마 태그 중간 테이블 고유 번호)
     * */
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * ThemeTag FK(슈퍼키 서브키)
     * PK 이자 FK 연결 Tag의 PK 와 연결됌 (N : 1)
     * */
    @MapsId
    @JoinColumn(name = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    /**
     * Theme (테마와 연관 관계) (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Theme theme;

    /**
     * ThemeTag Status (테마 태그 중간 테이블 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;
}

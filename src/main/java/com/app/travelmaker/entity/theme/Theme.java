package com.app.travelmaker.entity.theme;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Theme Entity (테마)
 * */

@Entity
@Table(name = "TBL_THEME")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_THEME SET DELETED = 1 WHERE ID = ?")
public class Theme extends Period {
    /**
     * Theme PK (테마 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;


    /**
     * Theme Title (테마 제목)
     * */
    @NotNull private String themeTitle;

    /**
     * Theme Content (테마 내용)
     * */
    @NotNull private String themeContent;

    /**
     * Theme Start Date (테마 시작 일)
     * */
    @NotNull private LocalDateTime themeStartDate;

    /**
     * Theme End Date (테마 종료 일)
     * */
    @NotNull private LocalDateTime themeEndDate;


    /**
     * Theme Status (테마 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

    /**
     * Member Entity 와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}

package com.app.travelmaker.entity.community;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Community Entity (커뮤니티)
 * */

@Entity
@Table(name = "TBL_COMMUNITY")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_COMMUNITY SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Community extends Period {

    /**
     * Community PK (커뮤니티 고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Community Title (커뮤니티 제목)
     * */
    @NotNull private String communityTitle;

    /**
     * Community Content (커뮤니티 내용)
     * */
    @NotNull private String communityContent;

    /**
     * Community Category (커뮤니티 카테고리)
     * */
    @NotNull private String communityCategory;


    /**
     * Member 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}

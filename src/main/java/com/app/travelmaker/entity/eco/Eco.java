package com.app.travelmaker.entity.eco;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Eco Entity (에코 인증)
 * */

@Entity
@Table(name = "TBL_ECO")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_ECO SET DELETED = 1 WHERE ID = ?")
public class Eco extends Period {

    /**
     * Eco PK (에코 인증  고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Eco Title (에코 인증 제목)
     * */
    @NotNull private String ecoTitle;

    /**
     * Eco Content (에코 인증 내용)
     * */
    @NotNull private String ecoContent;

    /**
     * Eco Status (에코 인증 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

    /**
     * Member Entity 와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}

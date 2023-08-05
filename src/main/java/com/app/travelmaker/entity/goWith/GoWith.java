package com.app.travelmaker.entity.goWith;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.constant.MbtiType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * GoWith Entity (같이 가요)
 * */

@Entity
@Table(name = "TBL_GOWITH")
@Getter @ToString
@SQLDelete(sql = "UPDATE TBL_GOWITH SET DELETED = 1 WHERE ID = ?")
public class GoWith extends Period {

    /**
     * GoWith PK (같이가요 고유 번호)
     * */
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * GoWith Title (같이가요 제목)
     * */
    @NotNull private String goWithTitle;

    /**
     * GoWith Content (같이가요 내용)
     * */
    @NotNull private String goWithContent;


    /**
     * GoWith Region Type (같이가요 여행 지역)
    * */
    @NotNull @Enumerated(EnumType.STRING)
    private GoWithRegionType goWithRegionType;

    /**
     * GoWith MBTI (같이가요 MBTI)
    * */
    @NotNull @Enumerated(EnumType.STRING)
    private MbtiType goWithMbti;


    /**
     * GoWith Status (같이가요 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;


    /**
     * Member Entity 와 연관 관계 (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}

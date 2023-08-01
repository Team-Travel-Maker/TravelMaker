package com.app.travelmaker.entity.point;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.type.PointCateGoryType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Point Entity (포인트)
 * */

@Entity
@Table(name = "TBL_POINT")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_POINT SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Point extends Period {
    /**
     * Point PK (포인트 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Point Histroy (포인트 사용/적립 내역)
     * */
    @NotNull private String pointHistory;

    /**
     * Point Category ( 사용/적립 )
     * */
    @NotNull private PointCateGoryType pointCateGoryType;

    /**
     * ECO와 연관 관계  (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Eco eco;

    /**
     * Member와 연관 관계  (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /**
     * Point Status (포인트 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;

}

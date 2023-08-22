package com.app.travelmaker.entity.point;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.entity.eco.Eco;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.constant.PointCateGoryType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Point Entity (포인트)
 * */

@Entity
@Table(name = "TBL_POINT")
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_POINT SET DELETED = 1 WHERE ID = ?")
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
    @Enumerated(EnumType.STRING)
    @NotNull private PointCateGoryType pointCateGoryType;

    /**
     * Point ( 적립, 사용 포인트 )
     * */
    @NotNull private Integer point;

    /**
     * Point Balance ( 포인트 잔액 )
     * */
    @NotNull private Integer pointBalance;

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

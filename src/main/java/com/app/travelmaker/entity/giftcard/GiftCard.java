package com.app.travelmaker.entity.giftcard;

import com.app.travelmaker.auditing.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Gift Card Entity (지역 상품권)
 * */

@Entity
@Table(name = "TBL_GIFT_CARD")
@Getter
@ToString
@SQLDelete(sql = "UPDATE TBL_GIFT_CARD SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class GiftCard extends Period {
    /**
     * Gift Card PK (상품권 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Gift Card Title (상품권 제목)
     * */
    @NotNull private String giftCardTitle;

    /**
     * Gift Card Region (상품권 사용 지역)
     * ex) 강원도
     * */
    @NotNull private String giftCardRegion;

    /**
     * Gift Card Region (상품권 사용 지역 상세)
     * ex) 춘천
     * */
    @NotNull private String giftCardRegionDetail;

    /**
     * Gift Card Price (상품권 사용 가격)
     * */
    @NotNull private Integer giftCardPrice;

    /**
     * Gift Card Status (상품권 삭제 상태)
     * */
    private boolean deleted = Boolean.FALSE;


}

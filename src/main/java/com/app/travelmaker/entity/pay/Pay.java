package com.app.travelmaker.entity.pay;

import com.app.travelmaker.auditing.Period;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.entity.giftcard.GiftCard;
import com.app.travelmaker.entity.mebmer.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Pay Entity (결제)
 * */

@Entity
@Table(name = "TBL_PAY")
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_PAY SET DELETED = 1 WHERE ID = ?")
public class Pay extends Period {
    /**
     * Pay PK (결제 고유 번호)
     * */
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Recipient Name  (받는 사람 이름)
     * */
    @NotNull private String recipientName;

    /**
     * Pay Total Count  (구매 상품권 개수)
     * */
    @NotNull private Integer payTotalCount;

    /**
     * Pay Total Price  (결제 포인트 총 가격)
     * */
    @NotNull private Integer payTotalPrice;

    /**
     * Pay Address(상품권 받을 주소)
     * */
    @Embedded @NotNull private Address address;

    /**
     * Member와 연관 관계  (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /**
     * GiftCard와 연관 관계  (N : 1)
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    private GiftCard giftCard;



}

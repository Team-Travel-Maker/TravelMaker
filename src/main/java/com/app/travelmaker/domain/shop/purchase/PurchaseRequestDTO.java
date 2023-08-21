package com.app.travelmaker.domain.shop.purchase;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.embeddable.address.Address;
import com.app.travelmaker.entity.giftcard.GiftCard;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.pay.Pay;
import com.app.travelmaker.entity.point.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PurchaseRequestDTO {
    private String recipientName;
    private Integer payTotalPrice;
    private Integer payTotalCount;
    private Address address;
    private Long memberId;
    private Long giftCardId;

    public Pay toPayEntity(Member member, GiftCard giftCard) {
        return Pay.builder()
                .address(address)
                .recipientName(recipientName)
                .payTotalPrice(payTotalPrice)
                .payTotalCount(payTotalCount)
                .member(member)
                .giftCard(giftCard)
                .build();
    }

    public Point toPointEntity(Member member, String giftCardTile) {
        return Point.builder()
                .member(member)
                .pointCateGoryType(PointCateGoryType.USE)
                .pointHistory(giftCardTile + " " + payTotalCount + "매 구매")
                .pointBalance(member.getMemberEcoPoint())
                .point(payTotalPrice)
                .build();
   }
}

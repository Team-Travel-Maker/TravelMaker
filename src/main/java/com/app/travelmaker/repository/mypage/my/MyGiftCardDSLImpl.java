package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyGiftCardDTO;
import com.app.travelmaker.domain.shop.GiftCardFileDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.app.travelmaker.entity.file.QFile.file;
import static com.app.travelmaker.entity.giftcard.QGiftCard.giftCard;
import static com.app.travelmaker.entity.giftcard.QGiftCardFile.giftCardFile;
import static com.app.travelmaker.entity.mebmer.QMember.member;
import static com.app.travelmaker.entity.pay.QPay.pay;

public class MyGiftCardDSLImpl implements MyGiftCardDSL{
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MyGiftCardDTO> getGiftCardListByMemberId(Long memberId) {

        return query.select(Projections.fields(MyGiftCardDTO.class,
                giftCard.id,
                giftCard.giftCardTitle,
                giftCard.giftCardRegion,
                giftCard.giftCardRegionDetail,
                pay.payTotalPrice,
                pay.payTotalCount,
                pay.recipientName,
                pay.updatedDate,
                file.fileName,
                file.fileUuid,
                file.filePath
                )
            ).from(giftCard, pay, giftCardFile, file)
                .where(giftCard.deleted.eq(false)
                        .and(giftCard.id.eq(pay.giftCard.id))
                        .and(giftCard.id.eq(giftCardFile.giftCard.id))
                        .and(giftCardFile.id.eq(file.id))
                        .and(pay.member.id.eq(memberId)))
                .orderBy(pay.id.desc())
                .fetch();

    }
}

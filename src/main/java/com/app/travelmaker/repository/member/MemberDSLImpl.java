package com.app.travelmaker.repository.member;

import com.app.travelmaker.entity.mebmer.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.app.travelmaker.entity.mebmer.QMember.*;

public class MemberDSLImpl implements MemberDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public long updateMemberPoints(Long memberId, Integer giftCardTotalPrice) {
        return query.update(member)
                .set(member.memberEcoPoint, member.memberEcoPoint.subtract(giftCardTotalPrice))
                .set(member.updatedDate, LocalDateTime.now())
                .where(member.id.eq(memberId))
                .execute();
    }
}

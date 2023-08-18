package com.app.travelmaker.repository.member;

import com.app.travelmaker.constant.Role;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.mebmer.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.travelmaker.entity.mebmer.QMember.*;
import static com.app.travelmaker.entity.notice.QNotice.notice;

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

    @Override
    public Page<Member> getList(Pageable pageable) {
        List<Member> members = query.selectFrom(member)
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count()).from(member).fetchOne();

        return new PageImpl<>(members, pageable, count);

    }

    @Override
    public void modifyStatus(Long id) {
        Boolean deleted = query.select(member.deleted).from(member).where(member.id.eq(id)).fetchOne();

        if(deleted){
            query.update(member)
                    .set(member.deleted,false)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }else{
            query.update(member)
                    .set(member.deleted,true)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }

    }

    @Override
    public void modifyType(Long id) {

        Role foundRole = query.select(member.memberRole).from(member).where(member.id.eq(id)).fetchOne();

        if(foundRole.getCode().equals("GENERAL")){
            query.update(member)
                    .set(member.memberRole, Role.COMPANY)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }else if(foundRole.getCode().equals("COMPANY")){
            query.update(member)
                    .set(member.memberRole, Role.GENERAL)
                    .set(member.updatedDate, LocalDateTime.now())
                    .where(member.id.eq(id))
                    .execute();
        }
    }
}

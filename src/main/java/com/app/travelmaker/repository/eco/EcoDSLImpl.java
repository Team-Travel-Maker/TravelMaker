package com.app.travelmaker.repository.eco;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.app.travelmaker.entity.eco.QEco.eco;

public class EcoDSLImpl implements EcoDSL {
    @Autowired
    private JPAQueryFactory query;


    @Override
    public Long getEcosCount(Long memberId) {
        return query.select(eco.count())
                .from(eco)
                .where(eco.member.id.eq(memberId)
                        .and(eco.deleted.eq(false)))
                .fetchOne();
    }
}

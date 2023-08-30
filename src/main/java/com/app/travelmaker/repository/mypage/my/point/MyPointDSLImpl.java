package com.app.travelmaker.repository.mypage.my.point;

import com.app.travelmaker.constant.PointCateGoryType;
import com.app.travelmaker.domain.mypage.my.point.MyPointDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.travelmaker.entity.point.QPoint.point1;
import static com.app.travelmaker.entity.store.QStore.store;

public class MyPointDSLImpl implements MyPointDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<MyPointDTO> getMyPoints(Long memberId) {
        return query.select(Projections.fields(MyPointDTO.class,
                point1.id,
                point1.pointHistory,
                point1.pointCateGoryType,
                point1.point,
                point1.pointBalance,
                point1.updatedDate)
        ).from(point1)
                .where(point1.member.id.eq(memberId)
                        .and(point1.deleted.eq(false)))
                .orderBy(point1.id.desc())
                .fetch();
    }

    @Override
    public Page<MyPointDTO> getMyPointsWithPage(Pageable pageable, Long memberId) {
        List<MyPointDTO> myPoints = query.select(Projections.fields(MyPointDTO.class,
                point1.id,
                point1.pointHistory,
                point1.pointCateGoryType,
                point1.point,
                point1.pointBalance,
                point1.updatedDate)
        ).from(point1)
                .where(point1.member.id.eq(memberId)
                        .and(point1.deleted.eq(false)))
                .orderBy(point1.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(point1.count())
                .from(point1)
                .where(point1.deleted.eq(false)
                        .and(point1.member.id.eq(memberId)))
                .fetchOne();

        return new PageImpl<>(myPoints, pageable, count);
    }

    @Override
    public List<MyPointDTO> getMyPointsByPointType(Long memberId, PointCateGoryType pointType) {
        return query.select(Projections.fields(MyPointDTO.class,
                point1.id,
                point1.pointHistory,
                point1.pointCateGoryType,
                point1.point,
                point1.pointBalance,
                point1.updatedDate)
        ).from(point1)
                .where(point1.member.id.eq(memberId)
                        .and(point1.pointCateGoryType.eq(pointType))
                        .and(point1.deleted.eq(false)))
                .orderBy(point1.id.desc())
                .fetch();
    }

    @Override
    public Page<MyPointDTO> getMyPointsByPointTypeWithPage(Pageable pageable, Long memberId, PointCateGoryType pointCateGoryType) {
        List<MyPointDTO> myPoints = query.select(Projections.fields(MyPointDTO.class,
                point1.id,
                point1.pointHistory,
                point1.pointCateGoryType,
                point1.point,
                point1.pointBalance,
                point1.updatedDate)
        ).from(point1)
                .where(point1.member.id.eq(memberId)
                        .and(point1.pointCateGoryType.eq(pointCateGoryType))
                        .and(point1.deleted.eq(false)))
                .orderBy(point1.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(point1.count())
                .from(point1)
                .where(point1.member.id.eq(memberId)
                        .and(point1.pointCateGoryType.eq(pointCateGoryType))
                        .and(point1.deleted.eq(false)))
                .fetchOne();

        return new PageImpl<>(myPoints, pageable, count);
    }
}

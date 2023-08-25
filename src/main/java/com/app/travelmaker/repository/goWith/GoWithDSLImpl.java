package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.entity.goWith.GoWith;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.travelmaker.entity.goWith.QGoWith.goWith;


public class GoWithDSLImpl implements GoWithDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Slice<GoWith> findAllWithSlice(Pageable pageable) {
        final List<GoWith> gowiths = query.selectFrom(goWith)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;

        if(gowiths.size() > pageable.getPageSize()){
            hasNext = true;
            gowiths.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(gowiths,pageable, hasNext);
    }
}
package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
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

    //지역
    @Override
    public Slice<GoWith> findAllWithSliceAndSorting(Pageable pageable, GoWithRegionType region) {
//        사용자가 요청한 페이지의 게시글 개수를 +1개 가져온다.
//        다음 페이지 유무를 알아야 화면 처리가 가능하기 때문!
        final List<GoWith> gowiths = query.selectFrom(goWith)
                .where(goWith.goWithRegionType.eq(region))
                .orderBy(goWith.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;

//        정말 한 개 더 가져왔다면, 다음 페이지가 있다는 의미이고,
//        원래 요청한 개수랑 동일하다면 다음 페이지가 없다는 의미이다.
        if (gowiths.size() > pageable.getPageSize()) {
            hasNext = true;
//            실제 화면에서는 요청한 개수만 필요하기 때문에
//            다음 페이지 유무 검사를 위해 가져온 마지막 게시글은 삭제해준다.
            gowiths.remove(pageable.getPageSize());
        }
//        Slice의 hasNext()를 사용할 경우 전달한 hasNext값이 나온다.
        return new SliceImpl<>(gowiths, pageable, hasNext);
    }

//무한 스크롤
    @Override
    public Slice<GoWith> findAllWithSlice(Pageable pageable) {
//        사용자가 요청한 페이지의 게시글 개수를 +1개 가져온다.
//        다음 페이지 유무를 알아야 화면 처리가 가능하기 때문!
        final List<GoWith> gowiths = query.selectFrom(goWith)
                .orderBy(goWith.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;

//        정말 한 개 더 가져왔다면, 다음 페이지가 있다는 의미이고,
//        원래 요청한 개수랑 동일하다면 다음 페이지가 없다는 의미이다.
        if (gowiths.size() > pageable.getPageSize()) {
            hasNext = true;
//            실제 화면에서는 요청한 개수만 필요하기 때문에
//            다음 페이지 유무 검사를 위해 가져온 마지막 게시글은 삭제해준다.
            gowiths.remove(pageable.getPageSize());
        }
//        Slice의 hasNext()를 사용할 경우 전달한 hasNext값이 나온다.
        return new SliceImpl<>(gowiths, pageable, hasNext);
    }
}
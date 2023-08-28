package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.domain.gowith.GoWithFileDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.goWith.QGoWith.goWith;
import static com.app.travelmaker.entity.goWith.QGoWithFile.goWithFile;


public class GoWithDSLImpl implements GoWithDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Slice<GoWith> findAllWithSliceAndSorting(Pageable pageable, GoWithRegionType region) {
//        사용자가 요청한 페이지의 게시글 개수를 +1개 가져온다.
//        다음 페이지 유무를 알아야 화면 처리가 가능하기 때문!
         List<GoWith> gowiths = query.selectFrom(goWith)
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

    @Override
    public Page<GoWithDTO> getList(Pageable pageable, GoWithRegionType region) {
        List<GoWithFileDTO> files = query.select(Projections.fields(GoWithFileDTO.class,
                goWithFile.id,
                goWithFile.fileName,
                goWithFile.filePath,
                goWithFile.fileSize,
                goWithFile.fileUuid,
                goWithFile.goWith.id.as("goWithId")
                )).from(goWithFile, goWith)
                .where(goWithFile.goWith.id.eq(goWith.id)
                .and(goWithFile.deleted.eq(false))).fetch();

        List<GoWithDTO> gowiths = query.select(Projections.fields(GoWithDTO.class,
                        goWith.id,
                        goWith.goWithTitle,
                        goWith.goWithContent,
                        goWith.goWithRegionType,
                        goWith.goWithMbti,
                        goWith.createdDate)
        ).from(goWith)
                .where(goWith.goWithRegionType.eq(region))
                .orderBy(goWith.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().peek(data ->{
                    if(files !=null) {
                        files.stream().filter(file -> file.getGoWithId().equals(data.getId())).forEach(
                                file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
        Long totalCount = query.select(goWith.count()).from(goWith).fetchOne();
        return new PageImpl<>(gowiths,pageable,totalCount);
    }

    @Override
    public Optional<GoWithDTO> findGoWithById(Long id) {
//        return Optional.ofNullable(query.selectFrom(goWith).where(goWith.id.eq(id)).fetchOne());
    }


    @Override
    public void delete(Long id) {

    }
}
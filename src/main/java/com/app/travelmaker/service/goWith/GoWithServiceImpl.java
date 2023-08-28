package com.app.travelmaker.service.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import com.app.travelmaker.repository.goWith.GoWithRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoWithServiceImpl implements GoWithService {
    private final GoWithRepository goWithRepository;

    @Override
    @Transactional
    public Slice<GoWith> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region) {
        return goWithRepository.findAllWithSliceAndSorting(pageable,region);
    }


    @Override
    @Transactional
    public Page<GoWithDTO> getList(Pageable pageable, GoWithRegionType region) {
        return goWithRepository.getList(pageable,region);
    }

    @Override
    @Transactional
    public GoWithDTO getGoWith(Long id) {
        return goWithRepository.getGoWith(id);
    }

    @Override
    @Transactional
    public void write(GoWithDTO goWithDTO) {
        goWithRepository.save(toEntity(goWithDTO));
    }

    @Override
    public void update(GoWithDTO goWithDTO) {

    }
//    @Override
//    @Transactional
//    public Slice<GoWithDTO> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region) {
////        Member member = QMember.member.();
//        return goWithRepository.findAllWithSliceAndSorting(pageable,region);
//    }

}

package com.app.travelmaker.service.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import com.app.travelmaker.repository.goWith.GoWithRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoWithServiceImpl implements GoWithService {
    private final GoWithRepository goWithRepository;

    @Override
    public Slice<GoWithDTO> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region) {
        return null;
    }

    @Override
    public Slice<GoWithDTO> getListBySlice(Pageable pageable) {
        return null;
    }

    @Override
    public GoWithDTO toDTO(GoWith goWith) {
        return null;
    }

//    @Override
//    @Transactional
//    public Slice<GoWithDTO> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region) {
////        Member member = QMember.member.();
//        return goWithRepository.findAllWithSliceAndSorting(pageable,region);
//    }

}

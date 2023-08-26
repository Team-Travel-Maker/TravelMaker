package com.app.travelmaker.service.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.mebmer.QMember;
import com.app.travelmaker.repository.goWith.GoWithRepository;
import lombok.RequiredArgsConstructor;
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
    public Slice<GoWithDTO> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region) {
//        Member member = QMember.member.();
        return goWithRepository.findAllWithSliceAndSorting(pageable,region);
    }

    @Override
    @Transactional
    public Slice<GoWithDTO> getListBySlice(Pageable pageable) {
        return goWithRepository.findAllWithSlice(pageable);
    }
}

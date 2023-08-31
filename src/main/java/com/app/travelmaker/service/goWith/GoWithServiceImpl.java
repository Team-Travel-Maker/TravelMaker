package com.app.travelmaker.service.goWith;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import com.app.travelmaker.entity.goWith.GoWithFile;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.goWith.GoWithFileRepository;
import com.app.travelmaker.repository.goWith.GoWithRepository;
import com.app.travelmaker.service.MemberSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoWithServiceImpl extends AccountSupport implements GoWithService, MemberSupport {
    private final GoWithRepository goWithRepository;
    private final GoWithFileRepository goWithFileRepository;
    @Override
    public Slice<GoWith> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region) {
        return goWithRepository.findAllWithSliceAndSorting(pageable,region);
    }


    @Override
    public Page<GoWithDTO> getList(Pageable pageable, GoWithRegionType region) {
        return goWithRepository.getList(pageable,region);
    }

    @Override
    public GoWithDTO getGoWith(Long id) {
        return goWithRepository.getGoWith(id);
    }

    @Override
    public void write(GoWithDTO goWithDTO) {
        Member member = toMemberEntity(authenticationInfo());
        GoWith goWith = goWithRepository.save(toEntity(goWithDTO, member));
        log.info("{}:",goWith);
        List<GoWithFile> Files = goWithFileRepository.saveAll(goWithDTO.getFiles().stream().map(goWithFileDTO -> toGoWithFileEntity(goWithFileDTO, goWith)).collect(Collectors.toList()));
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

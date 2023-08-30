package com.app.travelmaker.service.goWith;

import com.app.travelmaker.constant.GoWithRegionType;
import com.app.travelmaker.domain.gowith.GoWithDTO;
import com.app.travelmaker.domain.gowith.GoWithFileDTO;
import com.app.travelmaker.entity.goWith.GoWith;
import com.app.travelmaker.entity.goWith.GoWithFile;
import com.app.travelmaker.entity.mebmer.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GoWithService {
    //지역 정렬
    public Slice<GoWith> getListBySliceAndSorting(Pageable pageable, GoWithRegionType region);

    public Page<GoWithDTO> getList(Pageable pageable, GoWithRegionType region);

    public void write(GoWithDTO goWithDTO);
    public void update(GoWithDTO goWithDTO);
    public GoWithDTO getGoWith(Long id);

    default GoWith toEntity(GoWithDTO goWithDTO, Member member){
        return GoWith.builder().id(goWithDTO.getId())
                .member(member)
                .goWithTitle(goWithDTO.getGoWithTitle())
                .goWithContent(goWithDTO.getGoWithContent())
                .goWithMbti(goWithDTO.getGoWithMbti())
                .goWithRegionType((goWithDTO.getGoWithRegionType()))
                .createdDate(goWithDTO.getCreatedDate())
                .build();
    }

    default GoWithFile toGoWithFileEntity(GoWithFileDTO goWithFileDTO, GoWith goWith) {
        return GoWithFile.builder()
                .goWith(goWith)
                .fileName(goWithFileDTO.getFileName())
                .filePath(goWithFileDTO.getFilePath())
                .fileSize(goWithFileDTO.getFileSize())
                .fileType(goWithFileDTO.getFileType())
                .fileUuid(goWithFileDTO.getFileUuid())
                .build();
    }
}

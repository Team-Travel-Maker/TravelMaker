package com.app.travelmaker.service.cs;

import com.app.travelmaker.domain.cs.CustomServiceDTO;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.file.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomSerivceService {

    public Page<CustomService> getList(Pageable pageable);

    public CustomService findById(Long id);

    public void register(CustomServiceDTO customServiceDTO);

    public default CustomService toEntity(CustomServiceDTO customServiceDTO){
        return CustomService.builder()
                            .csTitle(customServiceDTO.getCsTitle())
                            .csContent(customServiceDTO.getCsContent())
                            .csType(customServiceDTO.getCsType())
                            .member(customServiceDTO.getMember())
                            .build();

    }


    public default File toEntity(FileDTO fileDTO){
        return File.builder()
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileSize(fileDTO.getFileSize())
                .fileUuid(fileDTO.getFileUuid())
                .fileType(fileDTO.getFileType())
                .build();
    }
}

package com.app.travelmaker.service.cs;

import com.app.travelmaker.domain.cs.CustomServiceDTO;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.entity.cs.CustomServiceFile;
import com.app.travelmaker.entity.file.File;

import java.util.Optional;

public interface CustomSerivceService {

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

    public default CustomServiceFile toEntity(CustomService customService){
        return CustomServiceFile.builder()
                                .customService(customService)
                                .build();
    }

    public default File toEntity(FileDTO fileDTO){
        return File.builder()
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileSize(fileDTO.getFileSize())
                .fileType(fileDTO.getFileType())
                .fileUuid(fileDTO.getFileUuid())
                .build();
    }
}

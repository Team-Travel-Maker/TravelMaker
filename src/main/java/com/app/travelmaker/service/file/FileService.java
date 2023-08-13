package com.app.travelmaker.service.file;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.file.File;

import java.util.Optional;

public interface FileService {

    public void register(FileDTO fileDTO);

    public Optional<File> findById(Long id);

    default File toEntity(FileDTO fileDTO){
        return File.builder().id(fileDTO.getId())
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileUuid(fileDTO.getFileUuid())
                .fileSize(fileDTO.getFileSize())
                .build();
    }

}

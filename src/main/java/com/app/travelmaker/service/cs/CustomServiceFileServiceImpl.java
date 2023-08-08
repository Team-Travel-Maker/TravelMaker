package com.app.travelmaker.service.cs;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.repository.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomServiceFileServiceImpl implements CustomServiceFileService {

    private final FileRepository fileRepository;
    @Override
    public void register(FileDTO fileDTO) {
        fileRepository.save(toEntity(fileDTO));
    }

    @Override
    public Optional<File> findById(Long id) {
        return fileRepository.findById(id);
    }
}

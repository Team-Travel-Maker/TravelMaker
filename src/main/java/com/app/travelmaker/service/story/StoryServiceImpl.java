package com.app.travelmaker.service.story;

import com.app.travelmaker.repository.story.StoryFileRepository;
import com.app.travelmaker.repository.story.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final StoryFileRepository storyFileRepository;

    @Override
    public ResponseEntity<Object> getList(Pageable pageable) {
        return storyRepository.getList(pageable);
    }

    @Override
    public ResponseEntity<Object> storyDelete(List<Long> ids) {
        ids.stream().forEach(id -> {
            storyRepository.findById(id)
                    .ifPresent(story ->{
                        story.getStoryFiles().forEach(file -> storyFileRepository.deleteById(file.getId()));
                    });
            storyRepository.deleteById(id);
        });

        return ResponseEntity.status(HttpStatus.OK)
                            .body("삭제 완료");
    }
}

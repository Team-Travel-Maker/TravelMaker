package com.app.travelmaker.repository.story;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StoryDSL {
    public ResponseEntity<Object> getList(Pageable pageable);

    Long getStoriesCount(Long memberId);
}

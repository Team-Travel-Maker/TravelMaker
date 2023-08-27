package com.app.travelmaker.service.story;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StoryService {

    public ResponseEntity<Object> getList(Pageable pageable);

    public ResponseEntity<Object> storyDelete(List<Long> ids);
}

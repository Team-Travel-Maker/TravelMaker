package com.app.travelmaker.repository.story;

import com.app.travelmaker.entity.story.StoryFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryFileRepository extends JpaRepository<StoryFile, Long> {
}

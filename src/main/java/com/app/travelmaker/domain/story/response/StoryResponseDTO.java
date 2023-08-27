package com.app.travelmaker.domain.story.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class StoryResponseDTO {

    private Long id;
    private String storyTitle;
    private String storyContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String memberName;
    private String memberEmail;
    private List<StoryFileResponseDTO> files = new ArrayList<>();
}

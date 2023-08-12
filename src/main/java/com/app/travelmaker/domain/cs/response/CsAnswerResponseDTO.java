package com.app.travelmaker.domain.cs.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Data
@NoArgsConstructor
public class CsAnswerResponseDTO {
    private Long id;
    private String answerContent;
    private Long customServiceId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean deleted;
}

package com.app.travelmaker.domain.cs.request;

import com.app.travelmaker.entity.cs.CustomService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CsAnswerDTO {
    private Long id;
    private LocalDateTime createdDate;
    private String answerContent;
    private CustomService customService;
}

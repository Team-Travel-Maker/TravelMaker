package com.app.travelmaker.domain.cs.response;

import com.app.travelmaker.constant.CsType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class CustomServiceResponseDTO {

    private Long id;
    private String csTitle;
    private String csContent;
    private CsType csType;
    private String memberName;
    private String memberEmail;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean deleted;
    private List<CustomServiceFileDTO> files= new ArrayList<>();
    private List<CsAnswerResponseDTO> csAnswers = new ArrayList<>();
}

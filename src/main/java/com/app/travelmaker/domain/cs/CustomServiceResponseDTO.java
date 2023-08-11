package com.app.travelmaker.domain.cs;

import com.app.travelmaker.constant.CsType;
import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.mebmer.Member;
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
    private List<File> files= new ArrayList<>();
}

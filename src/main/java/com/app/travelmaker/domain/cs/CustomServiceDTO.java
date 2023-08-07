package com.app.travelmaker.domain.cs;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.constant.CsType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class CustomServiceDTO {

    private Long id;
    private String csTitle;
    private String csContent;
    private CsType csType;
    private Member member;
    private List<FileDTO> files = new ArrayList<>();
    private List<Long> fileIdsForDelete = new ArrayList<>();
}

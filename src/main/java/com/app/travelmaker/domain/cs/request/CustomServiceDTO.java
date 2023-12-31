package com.app.travelmaker.domain.cs.request;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
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
    private MemberResponseDTO memberResponseDTO;
    private List<FileDTO> files = new ArrayList<>();
    private List<Long> fileIdsForDelete = new ArrayList<>();
}

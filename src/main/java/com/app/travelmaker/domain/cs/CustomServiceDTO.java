package com.app.travelmaker.domain.cs;

import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.constant.CsType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CustomServiceDTO {

    private Long id;
    private String csTitle;
    private String csContent;
    private CsType csType;
    private Member member;
}

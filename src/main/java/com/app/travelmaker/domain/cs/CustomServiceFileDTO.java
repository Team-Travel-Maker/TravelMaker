package com.app.travelmaker.domain.cs;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.cs.CustomService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CustomServiceFileDTO extends FileDTO {
    private CustomService customService;
}

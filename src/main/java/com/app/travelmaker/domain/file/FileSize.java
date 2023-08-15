package com.app.travelmaker.domain.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FileSize {

    private Integer height;
    private Integer width;
}

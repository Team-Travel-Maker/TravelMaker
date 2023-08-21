package com.app.travelmaker.domain.mypage.company;

import com.app.travelmaker.constant.FileType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreFileDTO {
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileType fileType;
    private Long fileSize;
    private Long storeId;
}

package com.app.travelmaker.domain.shop;

import com.app.travelmaker.constant.FileType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GiftCardFileDTO {
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileType fileType;
    private Long fileSize;
    private Long giftCardId;
}

package com.app.travelmaker.domain.notice.response;

import com.app.travelmaker.constant.FileType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class NoticeFileResponseDTO {
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileType fileType;
    private Long fileSize;
    private Long noticeId;
}
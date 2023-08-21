package com.app.travelmaker.domain.mypage.my;

import com.app.travelmaker.constant.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class MyStoryLikeFileDTO {
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileType fileType;
    private Long fileSize;
    private Long storyId;
}

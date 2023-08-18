package com.app.travelmaker.domain.notice.request;

import com.app.travelmaker.domain.file.FileDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class NoticeRequestDTO {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdDate;
    private List<FileDTO> files = new ArrayList<>();
    private List<Long> deleteFiles = new ArrayList<>();

}

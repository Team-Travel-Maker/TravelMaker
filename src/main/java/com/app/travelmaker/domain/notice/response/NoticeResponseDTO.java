package com.app.travelmaker.domain.notice.response;

import com.app.travelmaker.domain.cs.response.CustomServiceFileDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class NoticeResponseDTO {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean deleted;
    private List<NoticeFileResponseDTO> files= new ArrayList<>();
    private List<Long> deleteFiles = new ArrayList<>();
}

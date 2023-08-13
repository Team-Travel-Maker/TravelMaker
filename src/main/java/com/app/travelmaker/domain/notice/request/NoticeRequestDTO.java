package com.app.travelmaker.domain.notice.request;

import com.app.travelmaker.domain.file.FileDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class NoticeRequestDTO {

    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private List<FileDTO> files = new ArrayList<>();

}

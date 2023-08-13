package com.app.travelmaker.service.notice;

import com.app.travelmaker.domain.notice.request.NoticeRequestDTO;
import com.app.travelmaker.domain.notice.response.NoticeResponseDTO;
import com.app.travelmaker.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    /*공지 등록*/

    public void write(NoticeRequestDTO noticeRequestDTO);

    public Page<NoticeResponseDTO> getListWithPage(Pageable pageable);

    public NoticeResponseDTO detail(Long id);

    public void noticeDelete(List<Long> ids);


    default Notice toEntity(NoticeRequestDTO noticeRequestDTO){
        return Notice.builder()
                .noticeTitle(noticeRequestDTO.getNoticeTitle())
                .noticeContent(noticeRequestDTO.getNoticeContent())
                .build();
    }
}

package com.app.travelmaker.repository.notice;

import com.app.travelmaker.domain.cs.response.CustomServiceResponseDTO;
import com.app.travelmaker.domain.notice.response.NoticeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoticeDSL {

    public Page<NoticeResponseDTO> getListWithPage(Pageable pageable);

    public Optional<NoticeResponseDTO> detail(Long id);

}

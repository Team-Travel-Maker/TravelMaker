package com.app.travelmaker.repository.notice;

import com.app.travelmaker.entity.notice.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long>, NoticeFileDSL {
}

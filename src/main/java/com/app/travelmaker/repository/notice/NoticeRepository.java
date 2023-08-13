package com.app.travelmaker.repository.notice;

import com.app.travelmaker.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeDSL {
}

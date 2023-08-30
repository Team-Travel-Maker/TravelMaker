package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.entity.goWith.GoWithReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoWithReplyRepository extends JpaRepository<GoWithReply,Long>,GoWithFileDSL {
}

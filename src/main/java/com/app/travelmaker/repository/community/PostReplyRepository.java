package com.app.travelmaker.repository.community;

import com.app.travelmaker.entity.community.CommunityReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReplyRepository extends JpaRepository<CommunityReply, Long>, PostReplyDSL {
}

package com.app.travelmaker.repository.community;

import com.app.travelmaker.domain.community.ReplyDTO;

import java.util.Optional;

public interface PostReplyDSL {

    public void write(ReplyDTO replyDTO);

    public Optional<ReplyDTO> detail(Long id);

    Long getRepliesCount(Long id);

    public void modifyReply(ReplyDTO replyDTO);
}

package com.app.travelmaker.repository.community;

import com.app.travelmaker.domain.community.ReplyDTO;

import java.util.Optional;

public class PostReplyDSLImpl implements PostReplyDSL {
    @Override
    public void write(ReplyDTO replyDTO) {

    }

    @Override
    public Optional<ReplyDTO> detail(Long id) {
        return Optional.empty();
    }

    @Override
    public Long getRepliesCount(Long id) {
        return null;
    }

    @Override
    public void modifyReply(ReplyDTO replyDTO) {

    }
}

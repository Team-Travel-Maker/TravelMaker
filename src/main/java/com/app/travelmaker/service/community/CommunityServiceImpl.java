package com.app.travelmaker.service.community;


import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.repository.community.PostFileRepository;
import com.app.travelmaker.repository.community.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;

    @Override
    public List<PostDTO> getPostList(PostDTO postDTO) {
        return null;
    }

    @Override
    public void write(PostDTO postDTO) {
        postRepository.save(toEntity(postDTO));

    }

    @Override
    public PostDTO postDetail(Long id) {
        return null;
    }

    @Override
    public void postDelete(Long id) {

    }
}

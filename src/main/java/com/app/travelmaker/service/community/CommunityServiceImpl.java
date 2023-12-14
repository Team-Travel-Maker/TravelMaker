package com.app.travelmaker.service.community;


import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.repository.community.PostFileRepository;
import com.app.travelmaker.repository.community.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;

    @Override
    public List<PostDTO> getPostList(CommunityType communityType) {

        return postRepository.getPostList(communityType);
    }

    @Override
    public Long write(PostDTO postDTO) {
       return postRepository.save(toEntity(postDTO)).getId();
    }

    @Override
    public PostDTO detail(Long id) {
        System.out.println("service 컴인");
        return postRepository.detail(id).get();
    }



    @Override
    public void postDelete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void modifyPost(PostDTO postDTO) {
        postRepository.modifyPost(postDTO);
    }

//    @Override
//    public Optional<PostDTO> getPost(Long id) {
//        Optional getPost = Optional.ofNullable(postRepository.findById(id));
//        return getPost;
//    }
}

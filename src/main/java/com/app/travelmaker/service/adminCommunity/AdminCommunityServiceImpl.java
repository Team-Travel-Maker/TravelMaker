package com.app.travelmaker.service.adminCommunity;

import com.app.travelmaker.repository.community.PostFileRepository;
import com.app.travelmaker.repository.community.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommunityServiceImpl implements AdminCommunityService {

    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;

    @Override
    public ResponseEntity<Object> getList(Pageable pageable) {
        return postRepository.getList(pageable);
    }

    @Override
    public ResponseEntity<Object> communityDelete(List<Long> ids) {
        ids.stream().forEach(id -> {
            postRepository.findById(id)
                    .ifPresent(community ->{
                        community.getCommunityFiles().forEach(file -> postFileRepository.deleteById(file.getId()));
                    });
            postRepository.deleteById(id);
        });

        return ResponseEntity.status(HttpStatus.OK)
                .body("삭제 완료");
    }
}

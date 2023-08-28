package com.app.travelmaker.repository.community;

import com.app.travelmaker.domain.community.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostDSL {

    public List<PostDTO> getPostList(PostDTO postDTO);

    public Optional<PostDTO> detail(Long id);

    public void delete(Long id);




}

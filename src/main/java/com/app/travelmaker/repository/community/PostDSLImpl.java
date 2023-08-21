package com.app.travelmaker.repository.community;

import com.app.travelmaker.domain.community.PostDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostDSLImpl implements PostDSL {

    @Autowired
    private JPAQueryFactory query;


    @Override
    public List<PostDTO> getPostList(PostDTO postDTO) {


        return null;
    }

    @Override
    public Optional<PostDTO> detail(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}

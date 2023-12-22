package com.app.travelmaker.repository.tag;

import com.app.travelmaker.entity.tag.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagDSLImpl implements TagDSL{

    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Tag> getTagList() {
        return null;
    }
}

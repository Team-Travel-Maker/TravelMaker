package com.app.travelmaker.repository.tag;

import com.app.travelmaker.entity.tag.Tag;
import com.app.travelmaker.repository.community.PostDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, PostDSL {
}

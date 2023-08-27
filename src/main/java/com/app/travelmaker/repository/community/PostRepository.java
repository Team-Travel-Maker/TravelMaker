package com.app.travelmaker.repository.community;

import com.app.travelmaker.entity.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Community, Long>, PostDSL {
}

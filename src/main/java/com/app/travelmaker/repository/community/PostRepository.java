package com.app.travelmaker.repository.community;

import com.app.travelmaker.entity.community.Community;
import com.app.travelmaker.repository.adminCommunity.CommunityDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Community, Long>, PostDSL, CommunityDSL {
}

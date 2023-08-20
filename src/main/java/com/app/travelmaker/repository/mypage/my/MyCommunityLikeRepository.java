package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.entity.community.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCommunityLikeRepository extends JpaRepository<CommunityLike, Long>, MyCommunityLikeDSL {
}

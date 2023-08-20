package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.entity.story.StroyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyStoryLikeRepository extends JpaRepository<StroyLike, Long>, MyStoryLikeDSL {
}

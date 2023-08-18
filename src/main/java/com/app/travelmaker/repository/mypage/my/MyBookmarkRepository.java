package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.entity.theme.ThemeBookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookmarkRepository extends JpaRepository<ThemeBookMark, Long>, MyBookmarkDSL {
}

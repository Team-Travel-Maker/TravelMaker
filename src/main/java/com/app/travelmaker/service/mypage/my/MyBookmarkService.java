package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;

import java.util.List;

public interface MyBookmarkService {
    List<MyBookmarkDTO> getBookmarks();

    void deleteBookmark(Long bookmarkId);
}

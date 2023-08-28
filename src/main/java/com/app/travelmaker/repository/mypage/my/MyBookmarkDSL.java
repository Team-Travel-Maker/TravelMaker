package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;

import java.util.List;

public interface MyBookmarkDSL {
    List<MyBookmarkDTO> getBookMarks(Long id);

    Long getBookMarksCount(Long memberId);

    List<MyBookmarkDTO> getBookMarksMax4(Long memberId);
}

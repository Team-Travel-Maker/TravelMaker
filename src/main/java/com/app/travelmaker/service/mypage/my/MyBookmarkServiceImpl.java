package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.common.CommonSupport;
import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.mypage.my.MyBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBookmarkServiceImpl extends CommonSupport implements MyBookmarkService {
    private final MyBookmarkRepository myBookmarkRepository;

    @Override
    public List<MyBookmarkDTO> getBookmarks() {
        Member member = authenticationInfo();
        return myBookmarkRepository.getBookMarks(member.getId());
    }

    @Override
    public void deleteBookmark(Long bookmarkId) {
        myBookmarkRepository.deleteById(bookmarkId);
    }
}

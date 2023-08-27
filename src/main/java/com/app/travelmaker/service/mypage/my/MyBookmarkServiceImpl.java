package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.domain.mypage.my.MyBookmarkDTO;
import com.app.travelmaker.repository.mypage.my.MyBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBookmarkServiceImpl extends AccountSupport implements MyBookmarkService {
    private final MyBookmarkRepository myBookmarkRepository;

    @Override
    public List<MyBookmarkDTO> getBookmarks() {
        Long memberId = authenticationInfo().getId();
        return myBookmarkRepository.getBookMarks(memberId);
    }

    @Override
    public void deleteBookmark(Long bookmarkId) {
        myBookmarkRepository.deleteById(bookmarkId);
    }
}

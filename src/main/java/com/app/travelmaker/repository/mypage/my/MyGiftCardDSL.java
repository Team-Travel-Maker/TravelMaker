package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyGiftCardDTO;

import java.util.List;

public interface MyGiftCardDSL {
    List<MyGiftCardDTO> getGiftCardListByMemberId(Long memberId);

    Long getGiftCardCount(Long memberId);
}

package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyGiftCardDTO;
import com.app.travelmaker.domain.shop.GiftCardDTO;

import java.util.List;

public interface MyGiftCardService {
    List<MyGiftCardDTO> getGiftCardListByMemberId();
}

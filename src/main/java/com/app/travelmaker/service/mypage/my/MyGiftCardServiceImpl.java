package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.common.CommonSupport;
import com.app.travelmaker.domain.mypage.my.MyGiftCardDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.mypage.my.MyGiftCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyGiftCardServiceImpl extends AccountSupport implements MyGiftCardService{

    private final MyGiftCardRepository myGiftCardRepository;

    @Override
    public List<MyGiftCardDTO> getGiftCardListByMemberId() {
        Long memberId = authenticationInfo().getId();

        return myGiftCardRepository.getGiftCardListByMemberId(memberId);
    }
}

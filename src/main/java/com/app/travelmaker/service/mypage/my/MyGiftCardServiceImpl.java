package com.app.travelmaker.service.mypage.my;

import com.app.travelmaker.domain.mypage.my.MyGiftCardDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.repository.mypage.my.MyGiftCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyGiftCardServiceImpl implements MyGiftCardService{

    private final MyGiftCardRepository myGiftCardRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<MyGiftCardDTO> getGiftCardListByMemberId() {
        // TODO 세션으로 member객체 받기
        Member member = memberRepository.findById(32L).orElseThrow(() -> new RuntimeException("해당 멤버 없음"));

        return myGiftCardRepository.getGiftCardListByMemberId(member.getId());
    }
}

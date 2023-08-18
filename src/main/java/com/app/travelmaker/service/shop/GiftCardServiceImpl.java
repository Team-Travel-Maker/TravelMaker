package com.app.travelmaker.service.shop;

import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.domain.mypage.MyPageGiftCardDTO;
import com.app.travelmaker.entity.giftcard.GiftCard;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.pay.Pay;
import com.app.travelmaker.entity.point.Point;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.repository.point.PointRepository;
import com.app.travelmaker.repository.shop.GiftCardRepository;
import com.app.travelmaker.repository.shop.purchase.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftCardServiceImpl implements GiftCardService{
    private final GiftCardRepository giftCardRepository;
    private final PayRepository payRepository;
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;

    @Override
    public List<GiftCardDTO> getGiftCardListByGiftCardRegion(String giftCardRegion) {
        return giftCardRepository.getGiftCardByRegion(giftCardRegion);
    }

    @Override
    public List<GiftCardDTO> getGiftCardList() {
        return giftCardRepository.getGiftCardAll();
    }

    @Override
    public void addPurchase(PurchaseRequestDTO request) {
        // 1. member 테이블에 포인트 갱신
        memberRepository.updateMemberPoints(request.getMemberId(), request.getPayTotalPrice());

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("사용자 없음 에러 발생"));

        GiftCard giftCard = giftCardRepository.findById(request.getGiftCardId())
                .orElseThrow(() -> new RuntimeException("상품권 없음 에러 발생"));

        // 2. pay 테이블에 구매 정보 등록
        Pay pay = payRepository.save(request.toPayEntity(member, giftCard));

        // 3. point 히스토리에 등록
        Point point = pointRepository.save(request.toPointEntity(member, giftCard.getGiftCardTitle()));
    }
}

package com.app.travelmaker.service.shop;

import com.app.travelmaker.common.CommonSupport;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.entity.giftcard.GiftCard;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.pay.Pay;
import com.app.travelmaker.entity.point.Point;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.repository.point.PointRepository;
import com.app.travelmaker.repository.shop.GiftCardRepository;
import com.app.travelmaker.repository.shop.purchase.PayRepository;
import com.app.travelmaker.service.MemberSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCardServiceImpl extends CommonSupport implements GiftCardService, MemberSupport {
    private final GiftCardRepository giftCardRepository;
    private final PayRepository payRepository;
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final HttpSession session;

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

        final MemberResponseDTO memberDTO = (MemberResponseDTO) session.getAttribute("member");
        memberDTO.setMemberEcoPoint(memberDTO.getMemberEcoPoint() - request.getPayTotalPrice());
        session.setAttribute("member", memberDTO);

        Member member = toMemberEntity(memberDTO);

        GiftCard giftCard = giftCardRepository.findById(request.getGiftCardId())
                .orElseThrow(() -> new RuntimeException("상품권 없음 에러 발생"));

        // 2. pay 테이블에 구매 정보 등록
        Pay pay = payRepository.save(request.toPayEntity(member, giftCard));

        // 3. point 히스토리에 등록
        Point point = pointRepository.save(request.toPointEntity(member, giftCard.getGiftCardTitle()));
    }
}

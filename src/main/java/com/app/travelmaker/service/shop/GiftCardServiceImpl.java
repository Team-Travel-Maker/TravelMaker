package com.app.travelmaker.service.shop;

import com.app.travelmaker.common.CommonSupport;
import com.app.travelmaker.constant.FileType;
import com.app.travelmaker.domain.file.FileSize;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.domain.notice.request.NoticeRequestDTO;
import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.entity.giftcard.GiftCard;
import com.app.travelmaker.entity.giftcard.GiftCardFile;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.notice.Notice;
import com.app.travelmaker.entity.notice.NoticeFile;
import com.app.travelmaker.entity.pay.Pay;
import com.app.travelmaker.entity.point.Point;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.repository.point.PointRepository;
import com.app.travelmaker.repository.shop.GiftCardFileRepository;
import com.app.travelmaker.repository.shop.GiftCardRepository;
import com.app.travelmaker.repository.shop.purchase.PayRepository;
import com.app.travelmaker.service.MemberSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private final GiftCardFileRepository giftCardFileRepository;

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

    @Override
    public void register(GiftCardDTO giftCardDTO) {
        Long id = giftCardRepository.save(toEntity(giftCardDTO)).getId();;

        if(giftCardDTO.getFiles().size() >0){
            for (int i = 0; i < giftCardDTO.getFiles().size(); i++) {

                GiftCard foundGift = giftCardRepository.findById(id).orElseThrow(() -> {
                    throw new RuntimeException();
                });
                giftCardFileRepository.save(GiftCardFile.builder().giftCard(foundGift)
                        .fileName(giftCardDTO.getFiles().get(i).getFileName())
                        .fileSize(giftCardDTO.getFiles().get(i).getFileSize())
                        .fileType(FileType.REPRESENTATIVE)
                        .fileUuid(giftCardDTO.getFiles().get(i).getFileUuid())
                        .filePath(giftCardDTO.getFiles().get(i).getFilePath())
                        .build());
            }
        }
    }

    @Override
    public ResponseEntity<Object> modifyGiftCard(GiftCardDTO giftCardDTO) {
        giftCardDTO.getDeleteFiles().forEach(id -> giftCardFileRepository.deleteById(id));
        GiftCard foundGiftCard = giftCardRepository.findById(giftCardDTO.getId()).orElseThrow(() -> {
            throw new RuntimeException();
        });
        giftCardDTO.setCreatedDate(foundGiftCard.getCreatedDate());

        giftCardRepository.save(toEntity(giftCardDTO));
        if(giftCardDTO.getFiles().size() >0){
            for (int i = 0; i < giftCardDTO.getFiles().size(); i++) {

                giftCardFileRepository.save(GiftCardFile.builder().giftCard(foundGiftCard)
                        .fileName(giftCardDTO.getFiles().get(i).getFileName())
                        .fileSize(giftCardDTO.getFiles().get(i).getFileSize())
                        .fileType(FileType.REPRESENTATIVE)
                        .fileUuid(giftCardDTO.getFiles().get(i).getFileUuid())
                        .filePath(giftCardDTO.getFiles().get(i).getFilePath())
                        .build());
            }
        }
        return ResponseEntity.ok("수정 완료");
    }


    @Override
    public ResponseEntity<Object> deleteGiftCards(List<Long> ids) {
        /*문의 삭제하면 안에 답변 파일 삭제 상태로 변경*/
        ids.stream().forEach(id -> {
            giftCardRepository.findById(id)
                    .ifPresent(giftCard ->{
                        giftCard.getGiftCardFiles().forEach(file -> giftCardFileRepository.deleteById(file.getId()));
                    });
            giftCardRepository.deleteById(id);
        });
        return ResponseEntity.ok("삭제 성공");
    }

    @Override
    public Page<GiftCardDTO> getListWithPage(Pageable pageable) {
        return giftCardRepository.getListWithPage(pageable);
    }

    @Override
    public ResponseEntity<Object> getDetail(Long id) {
        final GiftCard giftCard = giftCardRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("상품권 없음");
        });

        return ResponseEntity.ok(giftCard);
    }
}

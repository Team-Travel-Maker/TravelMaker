package com.app.travelmaker.repository.shop;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.domain.notice.response.NoticeFileResponseDTO;
import com.app.travelmaker.domain.notice.response.NoticeResponseDTO;
import com.app.travelmaker.domain.shop.GiftCardDTO;
import com.app.travelmaker.domain.shop.GiftCardFileDTO;
import com.app.travelmaker.domain.shop.purchase.PurchaseRequestDTO;
import com.app.travelmaker.entity.community.QCommunity;
import com.app.travelmaker.entity.giftcard.GiftCardFile;
import com.app.travelmaker.entity.giftcard.QGiftCard;
import com.app.travelmaker.entity.giftcard.QGiftCardFile;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.giftcard.QGiftCard.*;
import static com.app.travelmaker.entity.giftcard.QGiftCardFile.*;
import static com.app.travelmaker.entity.notice.QNotice.notice;
import static com.app.travelmaker.entity.notice.QNoticeFile.noticeFile;

public class GiftCardDSLImpl implements GiftCardDSL {
    @Autowired
    private JPAQueryFactory query;


    @Override
    public List<GiftCardDTO> getGiftCardByRegion(String giftCardRegion) {

        final List<GiftCardFileDTO> files =
                query.select(Projections.fields(GiftCardFileDTO.class,
                        giftCardFile.id,
                        giftCardFile.fileName,
                        giftCardFile.filePath,
                        giftCardFile.fileSize,
                        giftCardFile.fileUuid,
                        giftCardFile.fileType,
                        giftCardFile.giftCard.id.as("giftCardId")
                )).from(giftCardFile).where(giftCardFile.giftCard.id.eq(giftCard.id).and(giftCardFile.deleted.eq(false)).and(giftCard.giftCardRegion.eq(giftCardRegion))).fetch();

        return query.select(
                Projections.fields(GiftCardDTO.class,
                        giftCard.id,
                        giftCard.giftCardTitle,
                        giftCard.giftCardPrice,
                        giftCard.giftCardRegion,
                        giftCard.giftCardRegionDetail)
        ).from(giftCard)
                .where(giftCard.deleted.eq(false).and(giftCard.giftCardRegion.eq(giftCardRegion)))
                .orderBy(giftCard.id.desc())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getGiftCardId().equals(data.getId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<GiftCardDTO> getGiftCardAll() {
        final List<GiftCardFileDTO> files =
                query.select(Projections.fields(GiftCardFileDTO.class,
                        giftCardFile.id,
                        giftCardFile.fileName,
                        giftCardFile.filePath,
                        giftCardFile.fileSize,
                        giftCardFile.fileUuid,
                        giftCardFile.fileType,
                        giftCardFile.giftCard.id.as("giftCardId")
                )).from(giftCardFile).where(giftCardFile.giftCard.id.eq(giftCard.id).and(giftCardFile.deleted.eq(false))).fetch();

        return query.select(
                Projections.fields(GiftCardDTO.class,
                        giftCard.id,
                        giftCard.giftCardTitle,
                        giftCard.giftCardPrice,
                        giftCard.giftCardRegion,
                        giftCard.giftCardRegionDetail)
        ).from(giftCard)
                .where(giftCard.deleted.eq(false))
                .orderBy(giftCard.id.desc())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getGiftCardId().equals(data.getId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }



    @Override
    public Page<GiftCardDTO> getListWithPage(Pageable pageable) {

        List<GiftCardFileDTO> files = getFiles();

        List<GiftCardDTO> giftCards = query.select(Projections.fields(GiftCardDTO.class,
                giftCard.id,
                giftCard.giftCardTitle,
                giftCard.giftCardPrice,
                giftCard.giftCardRegion,
                giftCard.giftCardRegionDetail,
                giftCard.createdDate,
                giftCard.updatedDate
        )).from(giftCard)
                .where(giftCard.deleted.eq(false))
                .orderBy(giftCard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().peek(data -> {
                    if(files != null){
                        files.stream().filter(file -> file.getGiftCardId().equals(data.getId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());


        Long count = query.select(giftCard.count()).from(giftCard).fetchOne();

        return new PageImpl<>(giftCards, pageable, count);
    }




    private List<GiftCardFileDTO> getFiles() {
        return query.select(Projections.fields(GiftCardFileDTO.class,
                giftCardFile.id,
                giftCardFile.fileName,
                giftCardFile.filePath,
                giftCardFile.fileSize,
                giftCardFile.fileUuid,
                giftCardFile.fileType,
                giftCardFile.giftCard.id.as("giftCardId")
        )).from(giftCardFile).where(giftCardFile.giftCard.id.eq(giftCard.id).and(giftCardFile.deleted.eq(false))).fetch();
    }



}

package com.app.travelmaker.repository.shop;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.entity.file.File;
import com.app.travelmaker.entity.file.QFile;
import com.app.travelmaker.entity.giftcard.QGiftCardFile;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.app.travelmaker.entity.file.QFile.*;
import static com.app.travelmaker.entity.giftcard.QGiftCardFile.*;

public class GiftCardFileDSLImpl implements GiftCardFileDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<FileDTO> getGiftCardFilesByGiftCardId(Long id) {
        return query.select(
            Projections.fields(FileDTO.class,
                giftCardFile.id,
                giftCardFile.fileName,
                giftCardFile.fileUuid,
                giftCardFile.filePath,
                giftCardFile.fileType,
                giftCardFile.fileSize
            )
        ).from(giftCardFile).where(giftCardFile.giftCard.id.eq(id)).fetch();
    }
}

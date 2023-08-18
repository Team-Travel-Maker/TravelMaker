package com.app.travelmaker.repository.mypage.company;

import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.domain.mypage.company.StoreFileDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.store.QStore.store;
import static com.app.travelmaker.entity.store.QStoreFile.storeFile;

public class StoreDSLImpl implements StoreDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<StoreDTO> getAllStore(Long memberId) {
        final List<StoreFileDTO> files =
                query.select(Projections.fields(StoreFileDTO.class,
                        storeFile.id,
                        storeFile.fileName,
                        storeFile.filePath,
                        storeFile.fileSize,
                        storeFile.fileUuid,
                        storeFile.fileType,
                        storeFile.store.id.as("storeId")
                )).from(storeFile, store)
                        .where(storeFile.store.id.eq(store.id)
                                .and(store.member.id.eq(memberId))
                                .and(storeFile.deleted.eq(false))).fetch();

        return query.select(
                Projections.fields(StoreDTO.class,
                        store.id,
                        store.storeTitle,
                        store.storeContent,
                        store.address,
                        store.storeType,
                        store.storeStatus,
                        store.updatedDate)
        ).from(store)
                .where(store.member.id.eq(memberId)
                .and(store.deleted.eq(false)))
                .orderBy(store.id.desc())
                .fetch()
                .stream().peek(data -> {
                    if (files != null) {
                        files.stream().filter(file -> file.getStoreId().equals(data.getId())).forEach(file -> data.getFiles().add(file));
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public StoreDTO getStore(Long storeId) {
        final List<StoreFileDTO> files =
                query.select(Projections.fields(StoreFileDTO.class,
                        storeFile.id,
                        storeFile.fileName,
                        storeFile.filePath,
                        storeFile.fileSize,
                        storeFile.fileUuid,
                        storeFile.fileType,
                        storeFile.store.id.as("storeId")
                )).from(storeFile, store)
                        .where(storeFile.store.id.eq(store.id)
                                .and(store.id.eq(storeId))
                                .and(storeFile.deleted.eq(false))).fetch();

        StoreDTO storeDTO = query.select(
                Projections.fields(StoreDTO.class,
                        store.id,
                        store.storeTitle,
                        store.storeContent,
                        store.address,
                        store.storeType,
                        store.storeStatus,
                        store.updatedDate)
        ).from(store)
                .where(store.id.eq(storeId)
                        .and(store.deleted.eq(false)))
                .fetchOne();
        files.forEach(storeFileDTO -> storeDTO.getFiles().add(storeFileDTO));

        return storeDTO;
    }
}

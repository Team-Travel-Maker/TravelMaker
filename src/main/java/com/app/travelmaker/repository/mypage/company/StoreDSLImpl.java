package com.app.travelmaker.repository.mypage.company;

import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.domain.mypage.company.StoreFileDTO;
import com.app.travelmaker.domain.store.response.StoreResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.travelmaker.entity.mebmer.QMember.member;
import static com.app.travelmaker.entity.store.QStore.store;
import static com.app.travelmaker.entity.store.QStoreFile.storeFile;

@Slf4j
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
                                .and(storeFile.deleted.eq(false)))
                        .orderBy(storeFile.id.asc())
                        .fetch();

        StoreDTO storeDTO = query.select(
                Projections.fields(StoreDTO.class,
                        store.id,
                        store.storeTitle,
                        store.storeContent,
                        store.address,
                        store.storeType,
                        store.storeStatus,
                        store.storeResult,
                        store.updatedDate)
        ).from(store)
                .where(store.id.eq(storeId)
                        .and(store.deleted.eq(false)))
                .fetchOne();
        files.forEach(storeFileDTO -> storeDTO.getFiles().add(storeFileDTO));

        return storeDTO;
    }


    /*이종문*/
    @Override
    public Page<StoreResponseDTO> getList(Pageable pageable) {

        List<StoreFileDTO> files = getFiles();

        List<StoreResponseDTO> stores = query.select(Projections.fields(StoreResponseDTO.class,
                store.id,
                store.storeTitle,
                store.storeContent,
                store.address.address.as("address"),
                store.address.addressDetail.as("addressDetail"),
                store.address.postcode.as("postCode"),
                store.storeType,
                store.storeStatus,
                store.storeResult,
                store.createdDate,
                store.updatedDate,
                member.memberEmail
        )).from(store)
                .innerJoin(store.member, member).on(store.deleted.eq(false))
                .orderBy(store.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().peek(data -> {
                    if(files != null) {
                        files.stream().filter(file -> file.getStoreId().equals(data.getId())).forEach(file -> data.getStoreFiles().add(file));
                    }
                }).collect(Collectors.toList());

        Long count = query.select(store.count()).from(store).fetchOne();

        return new PageImpl<>(stores, pageable, count);

    }

    @Override
    public StoreResponseDTO getDetail(Long id) {

        StoreResponseDTO detail = query.select(Projections.fields(StoreResponseDTO.class,
                store.id,
                store.storeTitle,
                store.storeContent,
                store.address.address.as("address"),
                store.address.addressDetail.as("addressDetail"),
                store.address.postcode.as("postCode"),
                store.storeType,
                store.storeStatus,
                store.storeResult,
                store.createdDate,
                store.updatedDate,
                member.memberEmail
        )).from(store)
                .innerJoin(store.member, member)
                .orderBy(store.id.desc())
                .where(store.id.eq(id))
                .fetchOne();

        List<StoreFileDTO> files = getFiles();
        files =files.stream().filter(file -> file.getStoreId().equals(detail.getId())).collect(Collectors.toList());
        detail.setStoreFiles(files);

        return detail;
    }

    @Override
    public void modifyStatus(StoreResponseDTO result) {
        query.update(store)
                .set(store.storeStatus, result.getStoreStatus())
                .set(store.storeResult, result.getStoreResult())
                .set(store.updatedDate, LocalDateTime.now())
                .where(store.id.eq(result.getId()))
                .execute();
    }

    @Override
    public Long getCompanyCount(Long memberId) {
        return query.select(store.count())
                .from(store)
                .where(store.member.id.eq(memberId)
                .and(store.deleted.eq(false)))
                .fetchOne();
    }

    private List<StoreFileDTO> getFiles(){
        return query.select(Projections.fields(StoreFileDTO.class,
                storeFile.id,
                storeFile.fileName,
                storeFile.filePath,
                storeFile.fileSize,
                storeFile.fileUuid,
                storeFile.fileType,
                storeFile.store.id.as("storeId")
        )).from(storeFile).where(storeFile.store.id.eq(store.id).and(storeFile.deleted.eq(false))).fetch();
    }


}

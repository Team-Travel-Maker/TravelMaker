package com.app.travelmaker.service.mypage.company;

import com.app.travelmaker.domain.file.FileDTO;
import com.app.travelmaker.domain.mypage.company.StoreDTO;
import com.app.travelmaker.domain.mypage.company.StoreFileDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.entity.store.Store;
import com.app.travelmaker.entity.store.StoreFile;

import java.util.List;

public interface StoreService {
    void addStore(StoreDTO request);
    List<StoreDTO> getAllStore();
    void deleteStore(List<Long> storeIds);
    StoreDTO getStore(Long storeId);
    void updateStore(StoreDTO request);

    default Store toStoreEntity(StoreDTO storeDTO, Member member){
        return Store.builder()
                .member(member)
                .storeTitle(storeDTO.getStoreTitle())
                .storeContent(storeDTO.getStoreContent())
                .address(storeDTO.getAddress())
                .storeType(storeDTO.getStoreType())
                .storeStatus(storeDTO.getStoreStatus())
                .storeResult(storeDTO.getStoreResult())
                .build();
    }

    default StoreFile toStoreFileEntity(StoreFileDTO storeFileDTO, Store store) {
        return StoreFile.builder()
                .store(store)
                .fileName(storeFileDTO.getFileName())
                .filePath(storeFileDTO.getFilePath())
                .fileSize(storeFileDTO.getFileSize())
                .fileType(storeFileDTO.getFileType())
                .fileUuid(storeFileDTO.getFileUuid())
                .build();
    }

}

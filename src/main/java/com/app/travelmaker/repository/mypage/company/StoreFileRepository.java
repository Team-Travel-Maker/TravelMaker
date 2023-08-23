package com.app.travelmaker.repository.mypage.company;

import com.app.travelmaker.entity.store.Store;
import com.app.travelmaker.entity.store.StoreFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreFileRepository extends JpaRepository<StoreFile, Long>, StoreFileDSL {
    public void deleteAllByStoreId(Long storeId);
}

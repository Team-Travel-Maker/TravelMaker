package com.app.travelmaker.repository.mypage.company;

import com.app.travelmaker.entity.store.Store;
import com.app.travelmaker.entity.store.StoreFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreFileRepository extends JpaRepository<StoreFile, Long>, StoreFileDSL {
}

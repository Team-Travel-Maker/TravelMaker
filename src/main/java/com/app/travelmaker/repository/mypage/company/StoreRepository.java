package com.app.travelmaker.repository.mypage.company;

import com.app.travelmaker.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreDSL {
}

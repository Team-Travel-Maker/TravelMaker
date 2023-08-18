package com.app.travelmaker.repository.shop;

import com.app.travelmaker.entity.giftcard.GiftCardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardFileRepository extends JpaRepository<GiftCardFile, Long>, GiftCardFileDSL {
}

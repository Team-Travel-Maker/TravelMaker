package com.app.travelmaker.repository.shop;

import com.app.travelmaker.entity.giftcard.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard, Long>, GiftCardDSL {
}

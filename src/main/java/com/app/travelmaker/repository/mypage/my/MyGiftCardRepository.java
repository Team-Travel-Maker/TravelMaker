package com.app.travelmaker.repository.mypage.my;

import com.app.travelmaker.entity.pay.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyGiftCardRepository extends JpaRepository<Pay, Long>, MyGiftCardDSL {
}

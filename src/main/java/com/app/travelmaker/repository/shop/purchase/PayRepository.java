package com.app.travelmaker.repository.shop.purchase;

import com.app.travelmaker.entity.pay.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long>, PayDSL {
}

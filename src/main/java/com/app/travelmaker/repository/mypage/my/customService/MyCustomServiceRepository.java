package com.app.travelmaker.repository.mypage.my.customService;

import com.app.travelmaker.entity.cs.CustomService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCustomServiceRepository extends JpaRepository<CustomService, Long>, MyCustomServiceDSL {
}

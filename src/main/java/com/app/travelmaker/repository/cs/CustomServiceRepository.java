package com.app.travelmaker.repository.cs;

import com.app.travelmaker.entity.cs.CustomService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomServiceRepository extends JpaRepository<CustomService, Long>, CustomServiceDSL {


}

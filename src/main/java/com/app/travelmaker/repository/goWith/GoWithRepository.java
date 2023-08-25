package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.entity.goWith.GoWith;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoWithRepository extends JpaRepository<GoWith, Long> , GoWithDSL {
}

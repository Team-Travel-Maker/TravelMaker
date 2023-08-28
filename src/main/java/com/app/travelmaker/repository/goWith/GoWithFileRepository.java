package com.app.travelmaker.repository.goWith;

import com.app.travelmaker.entity.goWith.GoWithFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoWithFileRepository extends JpaRepository<GoWithFile,Long>, GoWithFileDSL {
}

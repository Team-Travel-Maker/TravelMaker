package com.app.travelmaker.repository.cs;

import com.app.travelmaker.entity.cs.CustomServiceFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomServiceFileRepository extends JpaRepository<CustomServiceFile, Long>, CustomServiceFileDSL {
}

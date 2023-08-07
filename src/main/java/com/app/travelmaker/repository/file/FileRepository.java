package com.app.travelmaker.repository.file;

import com.app.travelmaker.entity.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}

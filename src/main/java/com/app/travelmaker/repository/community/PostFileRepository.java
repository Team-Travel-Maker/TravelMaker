package com.app.travelmaker.repository.community;

import com.app.travelmaker.entity.community.CommuntiyFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileRepository extends JpaRepository<CommuntiyFile, Long>, PostFileDSL {
}

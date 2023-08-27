package com.app.travelmaker.repository.community;

import com.app.travelmaker.entity.community.CommunityFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileRepository extends JpaRepository<CommunityFile, Long>, PostFileDSL {
}

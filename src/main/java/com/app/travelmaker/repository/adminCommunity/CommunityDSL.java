package com.app.travelmaker.repository.adminCommunity;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommunityDSL {

    public ResponseEntity<Object> getList(Pageable pageable);
}

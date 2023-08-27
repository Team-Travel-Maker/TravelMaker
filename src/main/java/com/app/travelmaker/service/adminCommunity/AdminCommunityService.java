package com.app.travelmaker.service.adminCommunity;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminCommunityService {

    public ResponseEntity<Object> getList(Pageable pageable);

    public ResponseEntity<Object> communityDelete(List<Long> ids);

}

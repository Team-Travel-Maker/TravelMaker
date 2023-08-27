package com.app.travelmaker.controller.admin;

import com.app.travelmaker.service.adminCommunity.AdminCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admins/community")
public class CommunityAdminApiController {

    private final AdminCommunityService communityService;

    @GetMapping("")
    public ResponseEntity<Object> getList(@PageableDefault(page = 0,size = 10)Pageable pageable){
        return communityService.getList(pageable);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> getList(@RequestPart List<Long> ids){
        return communityService.communityDelete(ids);
    }
}

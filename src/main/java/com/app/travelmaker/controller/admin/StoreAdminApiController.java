package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.store.response.StoreResponseDTO;
import com.app.travelmaker.service.store.StoreAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
@RequestMapping("api/admins/store")
public class StoreAdminApiController {

    private final StoreAdminService storeService;

    @GetMapping("")
    public Page<StoreResponseDTO> getList(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return storeService.getList(pageable);
    }
}

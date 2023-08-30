package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.store.response.StoreResponseDTO;
import com.app.travelmaker.service.mypage.company.StoreService;
import com.app.travelmaker.service.store.StoreAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor =Exception.class)
@RequestMapping("api/admins/store")
public class StoreAdminApiController {

    private final StoreAdminService storeAdminService;

    @GetMapping("")
    public Page<StoreResponseDTO> getList(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return storeAdminService.getList(pageable);
    }

    @GetMapping("/main")
    public Page<StoreResponseDTO> getListForMain(@PageableDefault(page = 0, size = 12) Pageable pageable){
        return storeAdminService.getList(pageable);
    }

    @GetMapping("detail/{id}")
    public StoreResponseDTO detail(@PathVariable Long id){
        return storeAdminService.detail(id);
    }

    @PutMapping("modify")
    public void changeStatus(@RequestPart(value = "result") StoreResponseDTO result){
        log.info(result.toString());
        storeAdminService.modifyStatus(result);
    }

    @DeleteMapping("")
    public void delete(@RequestPart List<Long> ids){
        storeAdminService.deleteStore(ids);
    }
}

package com.app.travelmaker.controller.admin;

import com.app.travelmaker.domain.cs.CustomServiceDTO;
import com.app.travelmaker.domain.cs.CustomServiceResponseDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.repository.member.MemberRepository;
import com.app.travelmaker.service.cs.CustomSerivceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/admins/*")
public class AdminApiController {

    private final CustomSerivceService customSerivceService;

    @GetMapping("inquiry/list")
    public Page<CustomService> getList(@PageableDefault(page = 0, size = 10) Pageable pageable){

        Page<CustomService> customServices = customSerivceService.getList(pageable);
        log.info("여기");
        log.info(customServices.getContent().toString());
        return  customSerivceService.getList(pageable);
    }
}

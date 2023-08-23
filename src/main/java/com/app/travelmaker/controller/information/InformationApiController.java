package com.app.travelmaker.controller.information;


import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.domain.cs.request.CustomServiceDTO;
import com.app.travelmaker.service.cs.CustomSerivceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/informations")
@Transactional(rollbackFor =Exception.class)
public class InformationApiController extends AccountSupport {

    private final CustomSerivceService customSerivceService;
    private final HttpSession session;


    @PostMapping("")
    public void write(@RequestPart(required = true, value = "customServiceDTO") CustomServiceDTO customServiceDTO){
        customServiceDTO.setMemberResponseDTO(authenticationInfo());
        customSerivceService.register(customServiceDTO);
    }

}

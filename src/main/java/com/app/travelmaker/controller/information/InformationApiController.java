package com.app.travelmaker.controller.information;


import com.app.travelmaker.common.CommonSupport;
import com.app.travelmaker.domain.cs.request.CustomServiceDTO;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.service.cs.CustomSerivceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/informations")
@Transactional(rollbackFor =Exception.class)
public class InformationApiController {

    private final CustomSerivceService customSerivceService;
    private final HttpSession session;


    @PostMapping("")
    public void write(@RequestPart(required = true, value = "customServiceDTO") CustomServiceDTO customServiceDTO){
        final MemberResponseDTO memberDTO = (MemberResponseDTO) session.getAttribute("member");
        customServiceDTO.setMemberResponseDTO(memberDTO);
        customSerivceService.register(customServiceDTO);
    }

}

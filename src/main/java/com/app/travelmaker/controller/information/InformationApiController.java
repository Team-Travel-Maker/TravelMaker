package com.app.travelmaker.controller.information;


import com.app.travelmaker.domain.cs.CustomServiceDTO;
import com.app.travelmaker.entity.cs.CustomService;
import com.app.travelmaker.service.cs.CustomSerivceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/informations/*")
public class InformationApiController {

    private final CustomSerivceService customSerivceService;

    @PostMapping("write")
    public void write(@RequestPart(required = true, value = "customServiceDTO") CustomServiceDTO customServiceDTO){
        log.info(customServiceDTO.toString());
        customSerivceService.register(customServiceDTO);
    }

}

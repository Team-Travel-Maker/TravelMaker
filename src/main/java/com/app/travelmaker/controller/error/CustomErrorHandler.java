package com.app.travelmaker.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/*")
@Slf4j
public class CustomErrorHandler {
    @GetMapping("403")
    public void error403(){;}

    @GetMapping("401")
    public void error401(){;}

    @GetMapping("500")
    public void error500(){;}
}

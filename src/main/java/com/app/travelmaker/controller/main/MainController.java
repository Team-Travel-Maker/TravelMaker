package com.app.travelmaker.controller.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/main/*")
public class MainController {
    // 메인
    @GetMapping("main")
    public void goToMain(){;}

}

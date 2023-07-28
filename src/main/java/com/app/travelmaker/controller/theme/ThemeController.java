package com.app.travelmaker.controller.theme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/themes/*")
public class ThemeController {

    @GetMapping("/list")
    public void goToList(){;}

    @GetMapping("/detail")
    public void goToDetail(){;}
}

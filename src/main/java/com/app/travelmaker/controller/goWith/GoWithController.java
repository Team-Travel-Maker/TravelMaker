package com.app.travelmaker.controller.goWith;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/goWith/*")
public class GoWithController {



//    함께가요 목록
    @GetMapping("goWith-list")
    public void goToGoWithList(){


    }

//    함께가요 등록
    @GetMapping("goWith-registration")
    public void goToGoWithRegistration(){;}

//    함께가요 수정
    @GetMapping("goWith-edit")
    public void goToGoWithEdit(){;}

//    함께가요 상세
    @GetMapping("goWith-detail")
    public void goToGoWithDetail(){;}









}

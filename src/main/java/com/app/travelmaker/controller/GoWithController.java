package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/go-with/*")
public class GoWithController {

//    함께가요 목록
    @GetMapping("go-with")
    public void goToGoWith(){;}

//    함께가요 등록


//    함께가요 수정








}

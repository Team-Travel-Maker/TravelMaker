package com.app.travelmaker.controller.eco;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/ecos/*")
public class EcoController {

//    에코인증 목록
    @GetMapping("eco-list")
    public void goToEcoList(){;}

//    에코인증 등록
    @GetMapping("eco-registration")
    public void goToRegistration(){;}

//    에코인증 상세
    @GetMapping("eco-detail")
    public void goToDetail(){;}

//    나의 에코인증 목록
    @GetMapping("my-eco")
    public void goToMyEco(){;}

//    에코인증 수정
    @GetMapping("eco-edit")
    public void goToEcoEdit(){;}














}

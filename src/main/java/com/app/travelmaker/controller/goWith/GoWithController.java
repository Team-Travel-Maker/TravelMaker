package com.app.travelmaker.controller.goWith;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("goWith-detail/{id}")
    public String  getPost(@PathVariable Long id, Model model) {
        log.info("==========================================");
        log.info("==========================================");
        log.info("==========================================");
        log.info("==========================================");
        log.info("==========================================");
            model.addAttribute("goWithId", id);
        return "/goWith/goWith-detail";
    }
}







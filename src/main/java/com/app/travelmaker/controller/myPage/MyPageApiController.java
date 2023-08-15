package com.app.travelmaker.controller.myPage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/myPages/*")
public class MyPageApiController {
    @GetMapping
    public void goMyPage() {
        log.info("mypage컨트롤러 까지 옴");
    }

}

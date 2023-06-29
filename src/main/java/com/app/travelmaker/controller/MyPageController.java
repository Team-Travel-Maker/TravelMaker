package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/mypage/*")
public class MyPageController {
    @GetMapping("bookmarks")
    public void goToBookmarks(){;}

    @GetMapping("likes")
    public void goToLikes(){;}
}

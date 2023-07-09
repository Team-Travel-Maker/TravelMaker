package com.app.travelmaker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/stories/*")
public class StoryController {

    //업체 스토리 목록
    @GetMapping("list")
    public void goToStoryList(){;}

    //업체 스토리 상세
    @GetMapping("detail")
    public void goToStoryDetail(){;}

    //업체 스토리 등록
    @GetMapping("write")
    public void goToStoryWrite(){;}

    //업체 스토리 수정/삭제
    @GetMapping("edit")
    public void goToStoryEdit(){;}

}

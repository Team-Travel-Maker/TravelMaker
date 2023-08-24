package com.app.travelmaker.controller.community;

import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.service.community.CommunityService;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequestMapping("api/communities/*")
public class CommunityApiController {

    private final CommunityService communityService;

    @GetMapping("board/detail")
    public void goWriteForm(@ModelAttribute PostDTO postDTO){
        System.out.println("여기?");
    }


    @PostMapping("board/write")
    public RedirectView write(@ModelAttribute PostDTO postDTO, RedirectAttributes redirectAttributes){
        String postTitle = postDTO.getPostTitle();
        String postContent = postDTO.getPostContent();
        postDTO.getCommunityType();
        postDTO.getCreateTime();
        postDTO.getMemberName();
        postDTO.setCommunityType(CommunityType.REVIEW);
        postDTO.setMemberName("정범진");
        postDTO.getMemberName();
        postDTO.getMemberId();

        log.info("==[]==", postDTO.toString());
        System.out.println("POST");
        System.out.println(postDTO.getMemberId());
        System.out.println(postDTO.getMemberName());

        communityService.write(postDTO);


        redirectAttributes.addAttribute("id", postDTO.getId());

        return new RedirectView("/community/board/detail");
    }















//
//            log.info("{}", postDTO.getPostTitle());
//        log.info("{}", postDTO.getPostContent());
//        log.info("{}", postDTO.getId());
}

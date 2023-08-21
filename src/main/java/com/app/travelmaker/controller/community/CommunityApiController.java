package com.app.travelmaker.controller.community;

import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequestMapping("api/communities/*")
public class CommunityApiController {

    private final CommunityService communityService;

    @GetMapping("review/write")
    public void goWriteForm(PostDTO postDTO){;}

    @PostMapping("save")
    public RedirectView write(PostDTO postDTO, RedirectAttributes redirectAttributes){
        communityService.write(postDTO);

        redirectAttributes.addAttribute("id", postDTO.getId());

        return new RedirectView("community/board/detail");
    }





}

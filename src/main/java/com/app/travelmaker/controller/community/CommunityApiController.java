package com.app.travelmaker.controller.community;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.constant.CommunityType;
import com.app.travelmaker.domain.community.PostDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.service.community.CommunityService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequestMapping("api/communities/*")
public class CommunityApiController extends AccountSupport {

    private final CommunityService communityService;

    @GetMapping("board/list")
    public List<PostDTO> getList(PostDTO postDTO, Model model){
//        model.addAttribute("postList", postList);

        return communityService.getPostList(postDTO);
    }


    @GetMapping("board/detail")
    public String viewPostDetail(@Param("id") Long id, Model model){
        System.out.println(id);
        PostDTO postDTO = communityService.postDetail(id);

        if(postDTO != null){
            model.addAttribute("post", postDTO);
            return "board/detail";
        } else{
            return "error";
        }
    }

    @GetMapping("board/write")
    public void goToWrite(){;}


    @PostMapping("board/write")
    public RedirectView write(@RequestBody String dto, PostDTO postDTO, RedirectAttributes redirectAttributes, Model model){
        log.info("{}", postDTO.toString());
        String postTitle = postDTO.getPostTitle();
        String postContent = postDTO.getPostContent();


//        일단 하드코딩
//        postDTO.setCommunityType(CommunityType.REVIEW);

        String memberName = authenticationInfo().getMemberName();
        Long id = authenticationInfo().getId();



        System.out.println("멤버 ID : " + id);
        System.out.println("멤버 이름 : " + memberName);
        communityService.write(postDTO);
        model.addAttribute("postDTO", postDTO);

        redirectAttributes.addAttribute("id", postDTO.getId());

        return new RedirectView("/community/board/detail");
    }















}

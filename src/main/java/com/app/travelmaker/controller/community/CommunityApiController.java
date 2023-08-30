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
import org.springframework.web.servlet.ModelAndView;
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


    @GetMapping("board/detail/{id}")
    public String viewPostDetail(Model model, @PathVariable Long id){
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
    public RedirectView write(@RequestBody PostDTO postDTO, RedirectAttributes redirectAttributes, Model model) throws Exception{
        log.info("{}", postDTO.toString());
        String postTitle = postDTO.getPostTitle();
        String postContent = postDTO.getPostContent();

        String memberName = authenticationInfo().getMemberName();
        Long memberId = authenticationInfo().getId();
        Long id = postDTO.getId();

        postDTO.setMember(
                Member.builder()
                .id(memberId)
                .build()
        );

        System.out.println("멤버 ID : " + memberId);
        System.out.println("멤버 이름 : " + memberName);
        communityService.write(postDTO);
        model.addAttribute("postDTO", postDTO);

        redirectAttributes.addAttribute("id", postDTO.getId());

        return new RedirectView("/community/board/detail");
    }

    @GetMapping("board/delete")
    public ModelAndView postDelete (@PathVariable Long id){
        communityService.postDelete(id);
        return null;
    }















}

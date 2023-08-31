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


//    @GetMapping("board/detail/{id}")
//    public String viewPostDetail(Model model, @PathVariable Long id){
//        System.out.println(id);
//        PostDTO postDTO = communityService.postDetail(id);
//
//        if(postDTO != null){
//            model.addAttribute("post", postDTO);
//            return "board/detail";
//        } else{
//            return "error";
//        }
//    }

//    @ResponseBody
//    @GetMapping("board/detail/{id}")
//    public PostDTO goToBoardDetail(@PathVariable Long id){
//        System.out.println("상세페이지 로딩");
//        System.out.println("게시물 id: " + id);
//
//        PostDTO postDTO = communityService.postDetail(id);
//
//        String memberName = postDTO.getMember().getMemberName();
//        System.out.println("작성자 : " + memberName);
//        System.out.println("작성일 : " + postDTO.getCreateTime());
//
//
//        log.info("=={}==", postDTO.toString());
//
//        return postDTO;
//    }

//    @GetMapping("board/detail/{id}")
//    public ModelAndView goToBoardDetail(@PathVariable Long id){
//        System.out.println("상세 페이지 로딩");
//        System.out.println("게시물 id: " + id);
//
//        ModelAndView mv = new ModelAndView();
//
//        PostDTO postDTO = communityService.postDetail(id);
//
//        String memberName = postDTO.getMember().getMemberName();
//        System.out.println("작성자 : " + memberName);
//        System.out.println("작성일 : " + postDTO.getCreateTime());
//
//
//        log.info("=={}==", postDTO.toString());
//        mv.setViewName("board/detail");
//        mv.addObject("postDTO", postDTO);
//        mv.addObject("memberName", memberName);
//
//        return mv;
//    }


//    @PostMapping("board/write")
//    public RedirectView write(@RequestBody PostDTO postDTO, RedirectAttributes redirectAttributes, Model model) throws Exception{
//        log.info("{}", postDTO.toString());
//
//        String memberName = authenticationInfo().getMemberName();
//        Long memberId = authenticationInfo().getId();
//
//        postDTO.setMember(
//                Member.builder()
//                .id(memberId)
//                .build()
//        );
//
//        System.out.println("멤버 ID : " + memberId);
//        System.out.println("멤버 이름 : " + memberName);
//        Long id = communityService.write(postDTO);
//        model.addAttribute("postDTO", postDTO);
//        model.addAttribute("id", postDTO.getId());
//
//        System.out.println("글쓰기 완료");
//        return new RedirectView("detail/" + id, true);
//    }
//
//    @GetMapping("board/delete")
//    public ModelAndView postDelete (@PathVariable Long id){
//        ModelAndView mv = new ModelAndView();
//
//        mv.setViewName("/communities/board/list");
//        communityService.postDelete(id);
//        return mv;
//    }















}

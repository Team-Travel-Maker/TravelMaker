package com.app.travelmaker.controller.account;

import com.app.travelmaker.constant.MemberJoinAccountType;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

/*
@Controller
@Slf4j
public class OauthController {
    @GetMapping("")
    public RedirectView goToMain(HttpSession session, RedirectAttributes attributes){
        log.info("들어옴테스트");
        MemberResponseDTO memberDTO = ((MemberResponseDTO)session.getAttribute("member"));
        if(memberDTO.getMemberPhone() == null){
           */
/* attributes.addFlashAttribute("oauthMember", memberDTO.getMemberEmail());*//*

            session.invalidate();
            return new RedirectView("/accounts/join/join");
        }
        return new RedirectView("/main/main");
    }
}
*/

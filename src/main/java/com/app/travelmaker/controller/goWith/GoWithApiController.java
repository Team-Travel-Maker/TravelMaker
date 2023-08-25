package com.app.travelmaker.controller.goWith;


import com.app.travelmaker.provider.MemberDetail;
import com.app.travelmaker.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/goWith/*")
public class GoWithApiController {
    private final MemberRepository memberRepository;
    @GetMapping("list")
    public void goToList(@AuthenticationPrincipal MemberDetail memberDetail, HttpSession session){
        log.info("======{}", memberDetail.toString());
        if(memberDetail != null){ //일반 로그인
//            session.setAttribute("member",new MemberDTO(memberRepository.findByMemberId(memberDetail.getMemberId)).get());
        }else { //OAuth 로그인

        }
    }

}

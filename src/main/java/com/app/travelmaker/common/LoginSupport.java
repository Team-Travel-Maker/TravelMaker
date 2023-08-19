package com.app.travelmaker.common;

import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.provider.MemberDetail;
import com.app.travelmaker.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;

@Slf4j
public abstract class LoginSupport {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HttpSession httpSession;

    public void authenticationInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal()!=null){
            Member member = memberRepository.findById(((MemberDetail) authentication.getPrincipal()).getId()).orElseThrow(() -> {
                throw new RuntimeException("아이디가 없습니다");
            });
            httpSession.invalidate();
            httpSession.setAttribute("member", member);
        }else{
            throw new RuntimeException("인증 회원 정보 없음");
        }
    }
}

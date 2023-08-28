package com.app.travelmaker.common;

import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.entity.mebmer.Member;
import com.app.travelmaker.provider.MemberDetail;
import com.app.travelmaker.provider.MemberOauthDetail;
import com.app.travelmaker.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;

@Slf4j
public abstract class AccountSupport {

    public MemberResponseDTO authenticationInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal()!=null){
            if(authentication.getPrincipal() instanceof MemberOauthDetail){
                return ((MemberOauthDetail) authentication.getPrincipal()).getMemberResponseDTO();
            }else if(authentication.getPrincipal() instanceof  MemberDetail) {
                return ((MemberDetail) authentication.getPrincipal()).getMemberResponseDTO();
                }
        }else{
            throw new RuntimeException("인증 회원 정보 없음");
        }
        return null;
    }
}

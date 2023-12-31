package com.app.travelmaker.handler;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.constant.MemberJoinAccountType;
import com.app.travelmaker.constant.Role;
import com.app.travelmaker.domain.member.response.MemberResponseDTO;
import com.app.travelmaker.provider.MemberDetail;
import com.app.travelmaker.provider.MemberOauthDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationSuccessHandlerImpl extends AccountSupport implements AuthenticationSuccessHandler {
    private final HttpSession session;
    private static String path = "/";
    private final String ADMIN_PATH = "/admins/notice/list";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        List<? extends GrantedAuthority> list = authentication.getAuthorities().stream().collect(Collectors.toList());

        log.info(list.get(0).toString());

        /** 이미 다른 sns로 db에 가입된 경우 로그인 창으로 */
        if(authentication.getPrincipal() instanceof MemberOauthDetail && ((MemberOauthDetail) authentication.getPrincipal()).getMemberResponseDTO().getMemberEmail() ==null){
            session.invalidate();
            response.sendRedirect("/accounts/login/login?error=true&exception=error3");
            return;
        }


        /** 일반회원으로 이미 가입된 이메일로 oauth sns 로그인 시도*/
        if(authentication.getPrincipal() instanceof MemberOauthDetail
                && ((MemberOauthDetail) authentication.getPrincipal()).getMemberResponseDTO().getMemberJoinAccountType().equals(MemberJoinAccountType.GENERAL)
        ){
            session.invalidate();
            response.sendRedirect("/accounts/login/login?error=true&exception=error3");
            return;
        }

        /** 관리자 로그인 핸들러*/
        if(list.get(0).toString().equals(Role.ADMIN.getSecurityRole())){
            response.sendRedirect(ADMIN_PATH);
            return;
        }


        /** OAuth 로그인 하여 추가 정보 입력하다가 메인으로 넘어가면 다시 로그인 했을때 다시 추가정보입력하게 검사*/
        if(list.get(0).toString().equals(Role.WAIT.getSecurityRole())){
            MemberResponseDTO memberDTO = authenticationInfo();
            if(!memberDTO.getMemberJoinAccountType().equals(MemberJoinAccountType.GENERAL)){
                response.sendRedirect("/accounts/join/join");
                return;
            }
        }else if(!(authentication.getPrincipal() instanceof MemberDetail)){
            if (savedRequest != null) {
                String anotherPath = savedRequest.getRedirectUrl();
                // 세션에 저장된 객체를 다 사용한 뒤에는 지워줘서 메모리 누수 방지
                requestCache.removeRequest(request, response);
                response.sendRedirect(anotherPath);
                return;
            }else{
                response.sendRedirect(path);
                return;
            }
        }

           if(authentication.getPrincipal() instanceof MemberDetail){
            /** 일반 로그인 핸들러 기본적*/
            // 있을 경우 URI 등 정보를 가져와서 사용
            if (savedRequest != null) {
                String anotherPath = savedRequest.getRedirectUrl();
                // 세션에 저장된 객체를 다 사용한 뒤에는 지워줘서 메모리 누수 방지
                requestCache.removeRequest(request, response);
                response.sendRedirect(anotherPath);
                return;
            }else{
                response.sendRedirect(path);
                return;
            }
        }

    }



}

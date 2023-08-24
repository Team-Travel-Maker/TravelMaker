package com.app.travelmaker.interceptor;

import com.app.travelmaker.provider.MemberOauthDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MainInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String referer = request.getHeader("referer");

        /*로그인하면 로그인 accounts/ 루트막기*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof MemberOauthDetail){
            return true;
        }else if (!authentication.getPrincipal().toString().equals("anonymousUser")) {
            response.sendRedirect("/");
            return false;
        }else{
            return true;
        }
    }
}

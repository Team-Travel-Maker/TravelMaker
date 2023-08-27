package com.app.travelmaker.interceptor;

import com.app.travelmaker.common.AccountSupport;
import com.app.travelmaker.constant.MemberJoinAccountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor extends AccountSupport implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String referer = request.getHeader("referer");

        /** 일반 로그인 아니고 SNS 로그인 시도 시 회원가입 추가 정보창으로 이동할 수 있게 */
        if(authenticationInfo() != null && (!authenticationInfo().getMemberJoinAccountType().equals(MemberJoinAccountType.GENERAL))){
            return true;
        }

        if(referer == null){
            response.sendRedirect("/accounts/login/login");
            return false;
        }

        return true;
    }
}

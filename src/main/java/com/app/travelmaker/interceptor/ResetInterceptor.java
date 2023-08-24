package com.app.travelmaker.interceptor;

import com.app.travelmaker.provider.MemberOauthDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ResetInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("check") == null) {
            response.sendRedirect("/accounts/login/login");
            return false;
        }

        request.getSession().invalidate();
        return true;
    }

}

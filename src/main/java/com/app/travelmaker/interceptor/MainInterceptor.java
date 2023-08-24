package com.app.travelmaker.interceptor;

import com.app.travelmaker.constant.Role;
import com.app.travelmaker.provider.MemberOauthDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MainInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String referer = request.getHeader("referer");

        /*로그인하면 로그인 accounts/ 루트막기*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<? extends GrantedAuthority> list = authentication.getAuthorities().stream().collect(Collectors.toList());

        /*익명 유저 로그인 창 허용*/
        if(authentication.getPrincipal().toString().equals("anonymousUser")){
            return true;
        /* sns 계정 가입 이고, 아직 추가 정보를 입력안한 상태에서 다음 join 화면 갈 수 있게 하기*/
        }else if(list.get(0).toString().equals(Role.WAIT.getSecurityRole())
                && authentication.getPrincipal() instanceof MemberOauthDetail){
            return true;
            /*아예 로그인 한 상태면 메인으로 인터셉터*/
        } else if (!authentication.getPrincipal().toString().equals("anonymousUser")) {
            response.sendRedirect("/");
            return false;
        }else{
            return true;
        }
    }
}

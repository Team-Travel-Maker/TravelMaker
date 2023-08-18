package com.app.travelmaker.handler;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final String URI = "/accounts/login/login";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;

        /** error1 : 아이디 비밀번호 틀림*/
        if(exception instanceof BadCredentialsException){errorMessage = "error1";}
        /** error2 : 탈퇴 계정*/
        else if(exception instanceof InternalAuthenticationServiceException){errorMessage = "error2";}
        /** error3 : 알 수 없는 오류*/
        else {errorMessage="error3";}

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); /* 한글 인코딩 깨진 문제 방지 */

        setDefaultFailureUrl(URI + "?error=true&exception=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}

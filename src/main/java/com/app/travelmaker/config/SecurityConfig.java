package com.app.travelmaker.config;

import com.app.travelmaker.constant.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private static final String FAVICON_PATH = "/favicon.ico";
    /*private static final String MAIN_PATH = "/main/**";*/
        /*문의 글 쓰기*/
    private static final String INFORMATION_PATH = "/informations/inquiry/write"; 
    private static final String ADMIN_PATH = "/admins/**";
    private static final String LOGIN_PAGE = "/accounts/login/login";
    private static final String LOGIN_PAGE2 = "/accounts/password/input";
    private static final String LOGOUT_PATH = "/accounts/logout";

    private static final String REMEMBER_ME_TOKEN_KEY = "hava a nice day";
    private static final int REMEMBER_ME_TOKEN_EXPIRED = 60 * 60 * 24 * 14;

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;


/*    private final UserDetailsService userDetailsService;*/

//    DB에 있는 비밀번호와 사용자가 입력한 비밀번호를 검사할 때 사용하며,
//    사용자가 입력한 비밀번호는 passwordEncoder()를 사용한다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring()
                .mvcMatchers(FAVICON_PATH)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests() // spring security 시작
//                .antMatchers(MAIN_PATH).authenticated() // 보안 검사를 하겠다.
//                .antMatchers(ADMIN_PATH).hasRole(Role.ADMIN.name()) //ADMIN 권한이 있는 회원은 접근 가능
//                .antMatchers(BOARD_PATH).hasRole(Role.MEMBER.name())
//                .and()
//                .csrf().disable()
//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler) //인가 실패
//                .authenticationEntryPoint(authenticationEntryPoint); //인증 실패

//        AuthenticationManage가 UserDetailService의 loadUserByUsername(username)를 사용해줌으로써
//        DB에서 조회된 정보와 비교하여 인증을 진행한다.
        http
                .formLogin()
                .loginPage(LOGIN_PAGE) // 로그인 아이디만 입력한 페이지
                .usernameParameter("email_input")
                .loginProcessingUrl(LOGIN_PAGE2) // 로그인 과정 중간 페이지 이 페이지에서 파라미터 2개 넘겨서 성공하면 인증완료
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler) // 인증 성공 시 핸들러
                .failureHandler(authenticationFailureHandler) // 인증 실패 시 핸들러
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_PATH)) // logout 요청 경로를 가로챈다.
                .logoutSuccessUrl(LOGIN_PAGE) // logout 성공 시 이동할 경로 작성
                .invalidateHttpSession(Boolean.TRUE)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(ADMIN_PATH).hasRole(Role.ADMIN.name()) //ADMIN 권한이 있는 회원은 접근 가능
                .antMatchers(INFORMATION_PATH).hasAnyRole(Role.ADMIN.name(), Role.GENERAL.name(), Role.COMPANY.name()); //3 중 아무나
    /*            .and()
                .oauth2Login()
                .userInfoEndpoint();*/

        return http.build();
    }
}













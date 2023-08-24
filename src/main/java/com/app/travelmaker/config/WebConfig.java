package com.app.travelmaker.config;

import com.app.travelmaker.interceptor.LoginInterceptor;
import com.app.travelmaker.interceptor.MainInterceptor;
import com.app.travelmaker.interceptor.ResetInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MainInterceptor())
                .addPathPatterns("/accounts/**");//

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/accounts/password/**");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/accounts/account/**");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/accounts/join/**");
        registry.addInterceptor(new ResetInterceptor())
                .addPathPatterns("/accounts/ok/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:///C:/upload/");
    }

}

package com.maserhe.config;

import com.maserhe.controller.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述:
 *  处理拦截器
 * @author Maserhe
 * @create 2021-04-04 13:52
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Value("{server.servlet.context-path}")
    private String contentPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor).excludePathPatterns("/**/*.css").excludePathPatterns("/**/*.js").excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/*.jpeg").excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/site/register.html")
                .excludePathPatterns("/site/login.html")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/kaptcha");

    }


}

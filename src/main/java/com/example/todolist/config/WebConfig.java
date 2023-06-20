package com.example.todolist.config;

import com.example.todolist.component.LoginUserArgumentResolver;
import com.example.todolist.config.interceptor.LogInterceptor;
import com.example.todolist.config.interceptor.LoginCheckInterceptor;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * packageName : com.example.todolist.config
 * fileName : WebConfig
 * author : SHW
 * date : 2023-06-13
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-13   SHW     최초 생성
 */

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LogInterceptor())//LogInterceptor 등록
                .order(1)
                .addPathPatterns("/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/user/**", "/login-proc");
    }
}

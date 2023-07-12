package com.example.todolist.config.component;


import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class EncodingFilter implements Filter {

    //서블릿 컨테이너가 기본적으로 구현, FilterConfig를 통한 커스텀 한 Filter 설정 가능, 필터를 인스턴스화 한 후 정확히 한번 init 메소드 호출 가능
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Start filter checking");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    //서블릿 컨테이너가 기본적으로 구현, doFilter 메서드 내의 모든 스레드가 종료되거나 설정한 제한 시간이 지난 후 서블릿 컨테이너에 의해 한번 호출
    @Override
    public void destroy() {
        log.info("End filter checking");
        Filter.super.destroy();
    }
}

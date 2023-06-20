package com.example.todolist.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * packageName : com.example.todolist.config.interceptor
 * fileName : LogInterceptor
 * author : SHW
 * date : 2023-06-21
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-21   SHW     최초 생성
 */

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {//컨트롤 메서드가 실행되기 전
        String requestURI = request.getRequestURI();

        log.info("[interceptor] requestURI {}: " , requestURI);

        return true;  // false -> 이후에 진행을 하지 않는다.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {//컨트롤 메서드가 실행된 직후 view 페이지 렌더링 되기 전
        log.info("[interceptor] postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {//view 페이지 렌더링
        log.info("[interceptor] afterCompletion");
    }
}

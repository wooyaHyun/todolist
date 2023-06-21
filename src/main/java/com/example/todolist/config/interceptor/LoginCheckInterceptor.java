package com.example.todolist.config.interceptor;

import com.example.todolist.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * packageName : com.example.todolist.config.interceptor
 * fileName : LoginCheckInterceptor
 * author : SHW
 * date : 2023-06-21
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-21   SHW     최초 생성
 */

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info("LoginCheckInterceptor");
        HttpSession session = request.getSession(false);
        log.warn("session::: {}", session);
        if(session == null){

            response.sendRedirect("/user/login");
            return false;
        }

        log.info("[인증 사용자]");
        return true;
    }
}

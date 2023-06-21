package com.example.todolist.config.aop;


import com.example.todolist.config.auth.dto.SessionUser;
import com.example.todolist.domain.logs.MethodDsc;
import com.example.todolist.service.logs.LogsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;

@RequiredArgsConstructor
@Slf4j
@Aspect
@Component
public class LogAdvice {

    private final LogsService logsService;
    private final HttpSession httpSession;
    private final HttpServletRequest request;

    @Pointcut("execution(* com.example.todolist.service..*(..)) && !execution(* com.example.todolist.service.user..*(..))")
    private void advicePoint(){}

    @Before("advicePoint()")
    public void logAfter(JoinPoint joinPoint){
        log.info("########## AOP #########");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        if(!methodSignature.getName().equals("addLogs")){
            logsService.addLogs(user.getUserId(), request, methodSignature.getName().toLowerCase());
        }

        /*for(Object arg : joinPoint.getArgs()) {
            log.info(arg.toString());
        }*/
    }
}

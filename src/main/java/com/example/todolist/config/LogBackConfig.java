package com.example.todolist.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ch.qos.logback.classic.Level.*;

@Configuration
public class LogBackConfig {

    //공통 필드, 어펜더 별 설정을 달리 할 경우 지역변수로 변경
    private final LoggerContext logCtx = (LoggerContext)LoggerFactory.getILoggerFactory();

    private final String pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %magenta([%thread]) %highlight([%-3level]) %logger{5} - %msg %n";

    //어펜더 목록, 다른 어펜더가 필요할 경우 추가
    private ConsoleAppender<ILoggingEvent> consoleAppender;

    @Bean
    public void logConfig(){
        consoleAppender = getLogConsoleAppender();
        createLoggers();
    }

    private void createLoggers(){
        // 로거 이름, 로깅 레벨, 상위 로깅 설정 상속 여부 설정
        createLogger("root", INFO, true);
        createLogger("root", DEBUG, true);
        createLogger("jdbc", OFF, true);
        createLogger("jdbc.sqlonly", DEBUG, true);
        createLogger("jdbc.sqltiming", DEBUG, true);
        createLogger("{패키지 경로}", INFO, true);
        createLogger("com.example.todolist.controller", DEBUG, true);
        createLogger("com.example.todolist.service", WARN, true);
        createLogger("com.example.todolist.repository", INFO, true);
    }

    private void createLogger(String loggerName, Level logLevel, Boolean additive) {
        Logger logger = logCtx.getLogger(loggerName);

        logger.setAdditive(additive);
        logger.setLevel(logLevel);
        logger.addAppender(consoleAppender);
    }

    private ConsoleAppender<ILoggingEvent> getLogConsoleAppender() {
        final String appendName = "STDOUT";

        PatternLayoutEncoder consoleLogEncoder = createLogEncoder(pattern);
        return createLogConsoleAppender(appendName, consoleLogEncoder);
    }

    private PatternLayoutEncoder createLogEncoder(String pattern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(logCtx);
        encoder.setPattern(pattern);
        encoder.start();
        return encoder;
    }

    private ConsoleAppender<ILoggingEvent> createLogConsoleAppender(String appendName, PatternLayoutEncoder consoleLogEncoder) {
        ConsoleAppender<ILoggingEvent> logConsoleAppender = new ConsoleAppender<>();
        logConsoleAppender.setName(appendName);
        logConsoleAppender.setContext(logCtx);
        logConsoleAppender.setEncoder(consoleLogEncoder);
        logConsoleAppender.start();
        return logConsoleAppender;
    }
}

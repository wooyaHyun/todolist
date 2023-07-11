package com.example.todolist.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.*;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ch.qos.logback.classic.Level.*;

@Configuration
public class LogbackConfig {

    //공통 필드, 어펜더 별 설정을 달리 할 경우 지역변수로 변경
    private final LoggerContext logCtx = (LoggerContext)LoggerFactory.getILoggerFactory();

    private final String consolePattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %magenta([%thread]) %highlight([%-3level]) %logger{5} - %msg %n";

    private final String rollingPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS}  %logger{5} - %msg %n";
    //private final String LOG_NAME = "./logs/application.log";
    private final String LOG_NAME = "D:\\logs\\application.log";
    private final String LOG_NAME_PATTERN = "./logs/application-%d{yyyy-MM-dd-HH-mm}.%i.log";
    private final String maxFileSize = "10MB";
    private final int maxHistory = 2;



    //어펜더 목록, 다른 어펜더가 필요할 경우 추가
    private ConsoleAppender<ILoggingEvent> consoleAppender;
    private RollingFileAppender<ILoggingEvent> rollingAppender;

    @Bean
    public void logConsoleConfig(){
        consoleAppender = getLogConsoleAppender();
        rollingAppender = getLogRollingAppender();
        createLoggers();
    }

    private void createLoggers(){
        // 로거 이름, 로깅 레벨, 상위 로깅 설정 상속 여부 설정
        createLogger("root", INFO, true);
        createLogger("jdbc.sqlonly", DEBUG, false);
        createLogger("jdbc.sqltiming", OFF, false);
        createLogger("org.hibernate.SQL", DEBUG, false);
        createLogger("com.example.todolist.controller", DEBUG, false);
        createLogger("com.example.todolist.service", DEBUG, false);
        createLogger("com.example.todolist.domain", DEBUG, false);
        createLogger("com.example.todolist.config.auth.dto", DEBUG, false);
    }

    private void createLogger(String loggerName, Level logLevel, Boolean additive) {
        Logger logger = logCtx.getLogger(loggerName);

        logger.setAdditive(additive);
        logger.setLevel(logLevel);
        logger.addAppender(consoleAppender);
        logger.addAppender(rollingAppender);
    }

    private ConsoleAppender<ILoggingEvent> getLogConsoleAppender() {
        final String appendName = "STDOUT";
        PatternLayoutEncoder consoleLogEncoder = createLogEncoder(consolePattern);
        return createLogConsoleAppender(appendName, consoleLogEncoder);
    }

    private RollingFileAppender<ILoggingEvent> getLogRollingAppender() {
        final String appendName = "ROLLING_LOG_FILE";
        PatternLayoutEncoder rollingLogEncoder = createLogEncoder(rollingPattern);
        RollingFileAppender<ILoggingEvent> rollingFileAppender = createLogRollingAppender(appendName, rollingLogEncoder);
        SizeAndTimeBasedRollingPolicy rollingPolicy = createLogRollingPolicy(rollingFileAppender);

        rollingFileAppender.setRollingPolicy(rollingPolicy);
        rollingFileAppender.start();

        return rollingFileAppender;
    }

    private SizeAndTimeBasedRollingPolicy<RollingPolicy> createLogRollingPolicy(RollingFileAppender<ILoggingEvent> rollingLogAppender) {
        SizeAndTimeBasedRollingPolicy<RollingPolicy> policy = new SizeAndTimeBasedRollingPolicy<>();
        policy.setContext(logCtx);
        policy.setParent(rollingLogAppender);
        policy.setFileNamePattern(LOG_NAME_PATTERN);  // 일자별 로그파일 이름 적용
        policy.setMaxHistory(maxHistory);       // 일자별 백업 파일 보관 기간
        policy.setTotalSizeCap(FileSize.valueOf(maxFileSize));
        policy.setMaxFileSize(FileSize.valueOf(maxFileSize));
        policy.start();
        return policy;
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

    private RollingFileAppender<ILoggingEvent> createLogRollingAppender(String appendName, PatternLayoutEncoder rollingLogEncoder) {
        RollingFileAppender<ILoggingEvent> logRollingAppender = new RollingFileAppender<>();
        logRollingAppender.setName(appendName);
        logRollingAppender.setContext(logCtx);
        logRollingAppender.setFile(LOG_NAME);
        logRollingAppender.setEncoder(rollingLogEncoder);
        logRollingAppender.setAppend(true);

        return logRollingAppender;
    }

}

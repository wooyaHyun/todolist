package com.example.todolist.config;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.*;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ch.qos.logback.classic.Level.*;
import static ch.qos.logback.classic.Level.INFO;

@Configuration
public class LogBackRollingConfig {

    private FileAppender<ILoggingEvent> rollingFileAppender;

    //공통 필드, 어펜더 별 설정을 달리 할 경우 지역변수로 변경
    private final LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

    private final String LOG_NAME = "./logs/application.log";
    private final String LOG_NAME_PATTERN = "./logs/application-%d{yyyy-MM-dd-HH-mm}.log";


    private final String pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS}  %logger{5} - %msg %n";

    //어펜더 목록, 다른 어펜더가 필요할 경우 추가
    private FileAppender<ILoggingEvent> rollingAppender;

    @Bean
    public void logRollingConfig(){
        rollingAppender = new RollingFileAppender<>();
        rollingAppender = getLogRollingAppender();
        createLoggers();
    }

    private void createLoggers(){
        // 로거 이름, 로깅 레벨, 상위 로깅 설정 상속 여부 설정
        createLogger("root", INFO, true);
        createLogger("jdbc", OFF, false);
        createLogger("jdbc.sqlonly", DEBUG, false);
        createLogger("jdbc.sqltiming", DEBUG, false);
        createLogger("com.example.todolist.controller", DEBUG, false);
        createLogger("com.example.todolist.service", DEBUG, false);
        createLogger("com.example.todolist.repository", INFO, false);
    }

    private void createLogger(String loggerName, Level logLevel, Boolean additive) {
        Logger logger = logCtx.getLogger(loggerName);
        logger.setAdditive(additive);
        logger.setLevel(logLevel);
        logger.addAppender(rollingAppender);
    }


    private FileAppender<ILoggingEvent> getLogRollingAppender() {
        final String appendName = "ROLLING_OUT_FILE";
        PatternLayoutEncoder rollingLogEncoder = createLogEncoder(pattern);
        SizeAndTimeBasedRollingPolicy rollingPolicy = createLogRollingPolicy(LOG_NAME_PATTERN);

        return createRollingFileAppender(appendName, rollingLogEncoder, rollingPolicy);
    }

    private PatternLayoutEncoder createLogEncoder(String pattern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(logCtx);
        encoder.setPattern(pattern);
        encoder.start();
        return encoder;
    }


    private SizeAndTimeBasedRollingPolicy createLogRollingPolicy(String LOG_NAME_PATTERN) {
        SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();

        policy.setFileNamePattern(LOG_NAME_PATTERN);  // 일자별 로그파일 이름 적용
        policy.setMaxHistory(2);       // 일자별 백업 파일 보관 기간
        policy.setTotalSizeCap(FileSize.valueOf("30mb"));
        policy.setMaxFileSize(FileSize.valueOf("50mb"));
        policy.start();
        return policy;
    }

    private FileAppender<ILoggingEvent> createRollingFileAppender(String appendName, PatternLayoutEncoder rollingLogEncoder, SizeAndTimeBasedRollingPolicy rollingPolicy) {
        RollingFileAppender<ILoggingEvent> logFileAppender = new RollingFileAppender<>();
        logFileAppender.setName(appendName);
        logFileAppender.setContext(logCtx);
        logFileAppender.setEncoder(rollingLogEncoder);
        logFileAppender.setRollingPolicy(rollingPolicy);
        logFileAppender.setTriggeringPolicy(rollingPolicy);
        logFileAppender.setFile(LOG_NAME);
        logFileAppender.start();
        return logFileAppender;
    }
}

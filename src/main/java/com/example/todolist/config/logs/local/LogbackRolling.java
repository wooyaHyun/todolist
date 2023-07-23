package com.example.todolist.config.logs.local;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.RollingPolicy;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.classic.Level.*;
import static ch.qos.logback.classic.Level.DEBUG;

public class LogbackRolling {

    private final LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

    private final String ROLLING_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS}  %logger{5} - %msg %n";
    private final String FILE_NAME = "D:\\logs\\application.log";
    private final String LOG_NAME_PATTERN = "./logs/application-%d{yyyy-MM-dd-HH-mm}.%i.log";
    private final String MAX_FILE_SIZE = "10MB";
    private final int MAX_HISTORY = 2;

    private RollingFileAppender<ILoggingEvent> rollingAppender;

    public void logConfig(){
        rollingAppender = getLogAppender();
        createLoggers();
    }

    private void createLoggers(){
        // 로거 이름, 로깅 레벨, 상위 로깅 설정 상속 여부 설정
        createLogger("jdbc", OFF, false);
        createLogger("jdbc.sqlonly", DEBUG, false);
        createLogger("jdbc.sqltiming", OFF, false);
        createLogger("org.hibernate.SQL", DEBUG, false);
    }

    private void createLogger(String loggerName, Level logLevel, Boolean additive) {
        Logger logger = logCtx.getLogger(loggerName);
        logger.setAdditive(additive);
        logger.setLevel(logLevel);
        logger.addAppender(rollingAppender);
    }

    private RollingFileAppender<ILoggingEvent> getLogAppender() {
        final String appendName = "ROLLING_LOG_FILE";
        PatternLayoutEncoder rollingLogEncoder = createLogEncoder(ROLLING_PATTERN);
        RollingFileAppender<ILoggingEvent> rollingFileAppender = createLogAppender(appendName, rollingLogEncoder);
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
        policy.setMaxHistory(MAX_HISTORY);       // 일자별 백업 파일 보관 기간
        policy.setTotalSizeCap(FileSize.valueOf(MAX_FILE_SIZE));
        policy.setMaxFileSize(FileSize.valueOf(MAX_FILE_SIZE));
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

    private RollingFileAppender<ILoggingEvent> createLogAppender(String appendName, PatternLayoutEncoder rollingLogEncoder) {
        RollingFileAppender<ILoggingEvent> logRollingAppender = new RollingFileAppender<>();
        logRollingAppender.setName(appendName);
        logRollingAppender.setContext(logCtx);
        logRollingAppender.setFile(FILE_NAME);
        logRollingAppender.setEncoder(rollingLogEncoder);

        return logRollingAppender;
    }
}

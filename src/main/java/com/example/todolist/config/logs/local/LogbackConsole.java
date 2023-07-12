package com.example.todolist.config.logs.local;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.classic.Level.*;
import static ch.qos.logback.classic.Level.DEBUG;

public class LogbackConsole{

    //공통 필드, 어펜더 별 설정을 달리 할 경우 지역변수로 변경
    private final LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

    private final String CONSOLE_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} %magenta([%thread]) %highlight([%-3level]) %logger{5} - %msg %n";

    //어펜더 목록, 다른 어펜더가 필요할 경우 추가
    private ConsoleAppender<ILoggingEvent> consoleAppender;


    public void logConfig(){
        logCtx.reset();
        consoleAppender = getLogAppender();
        createLoggers();
    }

    private void createLoggers(){
        // 로거 이름, 로깅 레벨, 상위 로깅 설정 상속 여부 설정
        createLogger("root", INFO, true);
        createLogger("jdbc", OFF, false);
        createLogger("jdbc.sqlonly", DEBUG, false);
        createLogger("jdbc.sqltiming", OFF, false);
        createLogger("org.hibernate.SQL", DEBUG, false);
        createLogger("com.example.todolist.controller", DEBUG, false);
        createLogger("com.example.todolist.service", DEBUG, false);
        createLogger("com.example.todolist.domain", DEBUG, false);
    }

    private void createLogger(String loggerName, Level logLevel, Boolean additive) {

        Logger logger = logCtx.getLogger(loggerName);
        logger.setAdditive(additive);
        logger.setLevel(logLevel);

        logger.addAppender(consoleAppender);
    }

    private ConsoleAppender<ILoggingEvent> getLogAppender() {
        final String appendName = "STDOUT";
        PatternLayoutEncoder consoleLogEncoder = createLogEncoder(CONSOLE_PATTERN);
        ConsoleAppender<ILoggingEvent> logConsoleAppender = createLogAppender(appendName, consoleLogEncoder);
        logConsoleAppender.start();

        return logConsoleAppender;
    }

    private PatternLayoutEncoder createLogEncoder(String pattern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(logCtx);
        encoder.setPattern(pattern);
        encoder.start();
        return encoder;
    }

    private ConsoleAppender<ILoggingEvent> createLogAppender(String appendName, PatternLayoutEncoder consoleLogEncoder) {
        ConsoleAppender<ILoggingEvent> logConsoleAppender = new ConsoleAppender<>();
        logConsoleAppender.setName(appendName);
        logConsoleAppender.setContext(logCtx);
        logConsoleAppender.setEncoder(consoleLogEncoder);

        return logConsoleAppender;
    }
}

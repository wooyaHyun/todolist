package com.example.todolist.config.logs.local;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.RollingPolicy;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static ch.qos.logback.classic.Level.*;
import static ch.qos.logback.classic.Level.DEBUG;

@Profile("local")
@Configuration
public class LogbackConfig {

    @Bean
    public LogbackConsole logbackConsole(){
        LogbackConsole logbackConsole = new LogbackConsole();
        logbackConsole.logConfig();
        return logbackConsole;
    }

    @Bean
    public LogbackRolling logbackRolling(){
        LogbackRolling logbackRolling = new LogbackRolling();
        logbackRolling.logConfig();
        return logbackRolling;
    }
}

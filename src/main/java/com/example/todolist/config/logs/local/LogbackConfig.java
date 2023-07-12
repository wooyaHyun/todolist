package com.example.todolist.config.logs.local;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class LogbackConfig {

    @Bean
    public LogbackConsole logbackConsole() {
        LogbackConsole logbackConsole = new LogbackConsole();
        logbackConsole.logConfig();
        return logbackConsole;
    }

    @Bean
    public LogbackRolling logbackRolling() {
        LogbackRolling logbackRolling = new LogbackRolling();
        logbackRolling.logConfig();
        return logbackRolling;
    }
}

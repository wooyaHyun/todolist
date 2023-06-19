package com.example.todolist.config.auth;

import com.example.todolist.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * packageName : com.example.todolist.config
 * fileName : WebConfig
 * author : SHW
 * date : 2023-06-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-11   SHW     최초 생성
 */


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/", "/user/**", "/login-proc").permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()    // 추가
                                .requestMatchers("/ledgers/**", "/api/v1/**").hasRole(Role.USER.name())
                                .requestMatchers("/admins/**").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                /*.exceptionHandling((exceptionConfig) ->
                        exceptionConfig
                                .authenticationEntryPoint(exceptionConfig -> )
                                .accessDeniedHandler(accessDeniedHandler)
                )*/
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/user/login")
                                .loginProcessingUrl("/login-proc")
                                .defaultSuccessUrl("/ledgers", true)
                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/")
                )
                .userDetailsService(myUserDetailsService);

        return http.build();
    }

}

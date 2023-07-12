package com.example.todolist.config;

import com.example.todolist.config.auth.MyUserDetailsService;
import com.example.todolist.config.component.AuthFailHandler;
import com.example.todolist.domain.user.Role;
import com.example.todolist.dto.exception.ErrorResponse;
import com.example.todolist.exception.ErrorCode;
import com.example.todolist.exception.UserErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.PrintWriter;


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
    private final AuthFailHandler authFailHandler;


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
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig
                                //.authenticationEntryPoint(unauthorizedEntryPoint) /*interceptor에서 미인증 사용자 로그인 페이지로 이동하도록 처리*/
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/user/login")
                                .loginProcessingUrl("/login-proc")
                                .failureHandler(authFailHandler)
                                .defaultSuccessUrl("/ledgers", true)
                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/")
                )
                .userDetailsService(myUserDetailsService);

        return http.build();
    }

    public final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                ErrorCode errorCode = UserErrorCode.UNAUTHORIZED_USER;
                ErrorResponse fail = ErrorResponse.builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build();
                //response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };

    public  final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                ErrorCode errorCode = UserErrorCode.INACTIVE_USER;
                ErrorResponse fail = ErrorResponse.builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build();
                //response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };



}

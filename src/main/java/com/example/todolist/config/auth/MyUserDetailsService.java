package com.example.todolist.config.auth;

import com.example.todolist.config.auth.dto.SessionUser;
import com.example.todolist.domain.user.Member;
import com.example.todolist.domain.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName : com.example.todolist.config.auth
 * fileName : MyUserDetailsService
 * author : SHW
 * date : 2023-06-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-11   SHW     최초 생성
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("username: {}", username);
        logger.info("username: {}", username);
        logger.warn("username: {}", username);
        logger.error("username: {}", username);
        Member member = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException(username));

        httpSession.setAttribute("user", new SessionUser(member));

        return User.builder().username(member.getUserId()).password(member.getPassword()).roles(member.getRole().name()).build();
    }

}

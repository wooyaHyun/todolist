package com.example.todolist.config.auth;

import com.example.todolist.domain.user.Member;
import com.example.todolist.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("spring security :::" + username);
        return findByUsername(username);
    }


    @Transactional(readOnly = true)
    private UserDetails findByUsername(String username){
        Member member = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원 입니다..."));
        return User.builder()
                .username(member.getUserId())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }
}

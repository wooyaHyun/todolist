package com.example.todolist.service.user;

import com.example.todolist.domain.user.Member;
import com.example.todolist.domain.user.UserRepository;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import com.example.todolist.dto.user.MemberJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * packageName : com.example.todolist.service.user
 * fileName : UserService
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
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByUserId(String username) throws Exception{
        boolean flag = userRepository.existsByUserId(username);
        System.out.println("flag :::" + flag);

        if(flag == true){
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
        return flag;
    }

    public String addUser(MemberJoinRequestDto requestDto) {
        System.out.println("passwordEncoder" + passwordEncoder.encode(requestDto.getPassword()));
        return userRepository.save(requestDto.toEntity(passwordEncoder)).getUserId();
    }



}

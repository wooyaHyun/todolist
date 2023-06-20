package com.example.todolist.service.user;

import com.example.todolist.domain.user.Member;
import com.example.todolist.domain.user.UserRepository;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import com.example.todolist.dto.user.MemberJoinRequestDto;
import com.example.todolist.exception.RestApiException;
import com.example.todolist.exception.UserErrorCode;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByUserId(String username) throws Exception{
        boolean flag = userRepository.existsByUserId(username);
        if(flag){
            throw new RestApiException(UserErrorCode.DUPLICATED_MEMBER_REGISTER);
        }
        return flag;
    }

    public String addUser(MemberJoinRequestDto requestDto) {
        log.info("passwordEncoder {}" , passwordEncoder.encode(requestDto.getPassword()));
        return userRepository.save(requestDto.toEntity(passwordEncoder)).getUserId();
    }



}


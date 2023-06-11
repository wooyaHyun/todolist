package com.example.todolist.service.user;

import com.example.todolist.domain.user.Member;
import com.example.todolist.domain.user.UserRepository;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import com.example.todolist.dto.user.MemberJoinRequestDto;
import lombok.RequiredArgsConstructor;
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

    public boolean existsByUserId(String username) {
        return userRepository.existsByUserId(username);
    }

    public String addUser(MemberJoinRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUserId();
    }



}

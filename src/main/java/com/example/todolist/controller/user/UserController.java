package com.example.todolist.controller.user;

import com.example.todolist.dto.user.MemberJoinRequestDto;
import com.example.todolist.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * packageName : com.example.todolist.controller.user
 * fileName : UserController
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
@RestController
public class UserController {

    private final UserService userService;


    @ResponseBody
    @GetMapping("/user/{username}/exists")
    public ResponseEntity<Boolean> userDuplicatedChecked(@PathVariable String username) throws Exception{

        return ResponseEntity.ok(userService.existsByUserId(username));
    }
}

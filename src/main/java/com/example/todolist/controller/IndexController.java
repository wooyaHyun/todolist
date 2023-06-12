package com.example.todolist.controller;

import com.example.todolist.dto.user.MemberJoinRequestDto;
import com.example.todolist.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : com.example.todolist.controller
 * fileName : IndexController
 * author : SHW
 * date : 2023-05-22
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-05-22   SHW     최초 생성
 */

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/user/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/user/join")
    public String join(){
        return "user/join";
    }


    @GetMapping("/ledgers")
    public String ledgers(){
        return "ledger/ledger";
    }

    @GetMapping("/ledgers/save")
    public String ledgersSave(){
        return "ledger/ledgers-save";
    }

}

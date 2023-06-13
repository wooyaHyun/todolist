package com.example.todolist.controller;

import com.example.todolist.config.auth.dto.LoginUser;
import com.example.todolist.config.auth.dto.SessionUser;
import com.example.todolist.dto.ledger.LedgerResponseDto;
import com.example.todolist.dto.user.MemberJoinRequestDto;
import com.example.todolist.service.ledger.LedgerService;
import com.example.todolist.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private final LedgerService ledgerService;
    private final UserService userService;
    private final HttpSession httpSession;

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

    @PostMapping("/user/join")
    public String userJoin(@ModelAttribute @Valid MemberJoinRequestDto requestDto) {
        System.out.println("requestDto ::: " + requestDto.toString() );
        userService.addUser(requestDto);

        return "user/login";
    }

    @GetMapping("/admins")
    public String adminPage(){
        return "admin/admin";
    }

    @GetMapping("/ledgers")
    public String ledgers(@LoginUser SessionUser user, Model model){

        if(user != null){
            model.addAttribute("userName", user.getUserId());
            model.addAttribute("roles", user.getRole());
        }

        return "ledger/ledger";
    }

    @GetMapping("/ledgers/save")
    public String ledgersSave(){
        return "ledger/ledgers-save";
    }

    @GetMapping("/ledgers/update/{id}")
    public String ledgersUpdate(@PathVariable Long id, Model model){
        LedgerResponseDto dto = ledgerService.getLedgerOne(id);
        model.addAttribute("ledger", dto);
        return "ledger/ledgers-update";
    }

}

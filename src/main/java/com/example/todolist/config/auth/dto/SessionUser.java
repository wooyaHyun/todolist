package com.example.todolist.config.auth.dto;

import com.example.todolist.domain.user.Member;
import com.example.todolist.domain.user.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String userId;
    private String password;
    private Role role;

    public SessionUser(Member member) {
        this.userId = member.getUserId();
        this.password = member.getPassword();
        this.role = member.getRole();
    }
}

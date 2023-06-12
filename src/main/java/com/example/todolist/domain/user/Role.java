package com.example.todolist.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName : com.example.todolist.domain.user
 * fileName : Role
 * author : SHW
 * date : 2023-06-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-11   SHW     최초 생성
 */

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반사용자"),
    ADMIN("ROLE_ADMIN", "일반관리자");

    private final String key;
    private final String title;
}


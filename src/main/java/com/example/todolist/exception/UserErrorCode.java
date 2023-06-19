package com.example.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "User is unauthorized"),
    INACTIVE_USER(HttpStatus.FORBIDDEN, "User is inactive"),
    DUPLICATED_MEMBER_REGISTER(HttpStatus.BAD_REQUEST, "이미 사용 중인 회원 ID입니다. "),;

    private final HttpStatus httpStatus;
    private final String message;


}

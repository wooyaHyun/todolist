package com.example.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "로그인 후 이용가능한 서비스 입니다."),
    INACTIVE_USER(HttpStatus.FORBIDDEN, "접근 권한이 없습니다. "),
    DUPLICATED_MEMBER_REGISTER(HttpStatus.BAD_REQUEST, "이미 사용 중인 회원 ID입니다. "),
    ;

    private final HttpStatus httpStatus;
    private final String message;


}

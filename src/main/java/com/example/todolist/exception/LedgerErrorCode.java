package com.example.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LedgerErrorCode implements ErrorCode{
    ;


    private final HttpStatus httpStatus;
    private final String message;


}

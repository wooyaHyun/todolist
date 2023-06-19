package com.example.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LedgerErrorCode implements ErrorCode{
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception"),
    LEDGER_NOT_FOUND(HttpStatus.NOT_FOUND, "Ledger Not found"),;


    private final HttpStatus httpStatus;
    private final String message;


}

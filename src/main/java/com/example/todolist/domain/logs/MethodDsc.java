package com.example.todolist.domain.logs;

import com.example.todolist.domain.ledger.Item;
import com.example.todolist.domain.ledger.LedgerDsc;
import com.example.todolist.dto.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Locale;

/**
 * packageName : com.example.todolist.domain.logs
 * fileName : MethodDsc
 * author : SHW
 * date : 2023-06-22
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-22   SHW     최초 생성
 */


@RequiredArgsConstructor
public enum MethodDsc implements EnumMapperType {

    CREATE("등록", "add"),
    READ("조회", "anyname"),
    UPDATE("수정", "update"),
    DELETE("삭제", "delete"),;



    private final String title;
    private final String pattern;

    public static MethodDsc findByMethodDsc(String methodName) {
        return Arrays.stream(MethodDsc.values())
                .filter(dsc -> methodName.contains(dsc.getPattern()))
                .findAny()
                .orElse(READ);
    }


    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getPattern() { return pattern; }

}

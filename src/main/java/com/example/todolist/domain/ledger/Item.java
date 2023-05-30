package com.example.todolist.domain.ledger;

import com.example.todolist.dto.EnumMapperBasic;
import lombok.RequiredArgsConstructor;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : Item
 * author : SHW
 * date : 2023-04-13
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-13   SHW     최초 생성
 */
@RequiredArgsConstructor
public enum Item implements EnumMapperBasic {

    FOOD("식비"),
    SHOPPING("쇼핑"),
    COFFEE("커피"),
    DATE("데이트통장"),
    ALCOHOL("음주비"),
    TRANSPORTATION("교통비"),
    ETC_EXPENDITURE("기타지출"),
    SALARY("월급"),
    BONUS("성과급"),
    CLOTHING_PAYMENT("피복비"),
    GIFT_CARD("상품권"),
    ETC_INCOME("기타수입");


    private final String title;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }


}

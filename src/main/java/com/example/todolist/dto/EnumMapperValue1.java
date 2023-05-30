package com.example.todolist.dto;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : EnumMapperValueBasic
 * author : SHW
 * date : 2023-05-31
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-05-31   SHW     최초 생성
 */

public class EnumMapperValue1 {

    private String code;
    private String title;

    public EnumMapperValue1(EnumMapperBasic enumMapperBasic) {
        this.code = enumMapperBasic.getCode();
        this.title = enumMapperBasic.getTitle();

    }

    public String getCode(){return code;}
    public String getTitle(){return title;}

    @Override
    public String toString() {
        return "EnumMapperValue{" +
                "code='" + code + '\'' +
                ", title='" + title +
                '}';
    }
}

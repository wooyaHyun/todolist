package com.example.todolist.dto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : EnumMapperValue
 * author : SHW
 * date : 2023-05-30
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-05-30   SHW     최초 생성
 */

public class EnumMapperValue2 {

    private String code;
    private String title;
    private List<EnumMapperValue1> itemList;

    public EnumMapperValue2(EnumMapperType enumMapperType) {
        this.code = enumMapperType.getCode();
        this.title = enumMapperType.getTitle();
        this.itemList = enumMapperType.getItemList().stream().map(EnumMapperValue1::new).collect(Collectors.toList());
    }

    public String getCode(){return code;}
    public String getTitle(){return title;}
    public List<EnumMapperValue1> getItemList(){return itemList;}

    @Override
    public String toString() {
        return "EnumMapperValue{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}

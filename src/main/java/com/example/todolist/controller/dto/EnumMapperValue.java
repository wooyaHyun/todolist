package com.example.todolist.controller.dto;

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

public class EnumMapperValue {
    private final String code;
    private final String title;

    //private List<EnumMapperValue> itemList;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.code = enumMapperType.getCode();
        this.title = enumMapperType.getTitle();
    }

    /*public EnumMapperValue(EnumMapperType enumMapperType, List<EnumMapperValue> itemList) {
        this.code = enumMapperType.getCode();
        this.title = enumMapperType.getTitle();
        this.itemList = itemList;
    }*/

    public String getCode(){return code;}
    public String getTitle(){return title;}

    //public List<EnumMapperValue> getItemList(){return itemList;}

    @Override
    public String toString() {
        return "EnumMapperValue{" +
                "code='" + code + '\'' +
                ", title='" + title +
                '}';
    }
}

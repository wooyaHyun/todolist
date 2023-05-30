package com.example.todolist.domain.ledger;

import com.example.todolist.dto.EnumMapperType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : LedgerDsc
 * author : SHW
 * date : 2023-04-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-11   SHW     최초 생성
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // 추가
@RequiredArgsConstructor
public enum LedgerDsc implements EnumMapperType {
    EXPENDITURE("지출", Arrays.asList(Item.FOOD, Item.SHOPPING, Item.COFFEE, Item.DATE, Item.ALCOHOL, Item.TRANSPORTATION, Item.ETC_EXPENDITURE)),
    INCOME("수입", Arrays.asList(Item.SALARY, Item.BONUS, Item.CLOTHING_PAYMENT, Item.GIFT_CARD, Item.ETC_INCOME));
    //EMPTY("없음",Collections.EMPTY_LIST);

    private final String title;

    private final List<Item> itemList;

    public static LedgerDsc findByLedgerCode(Item item) {
        return Arrays.stream(LedgerDsc.values())
                .filter(ledgerDsc -> ledgerDsc.hasLedgerDsc(item))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("장부 항목에" + item.getTitle() + "가 존재하지 않습니다.")); // 임시로 IllegalArgumentException 사용 추후 exception도 custom해서 만들자...
    }

    public boolean hasLedgerDsc(Item item) {
        return itemList.stream()
                .anyMatch(item1 -> item1 == item);
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public List<Item> getItemList() {
        return itemList;
    }
}

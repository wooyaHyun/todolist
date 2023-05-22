package com.example.todolist.domain.ledger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
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

@Getter
@RequiredArgsConstructor
public enum LedgerDsc {
    EXPENDITURE("지출", Arrays.asList(Item.FOOD, Item.SHOPPING, Item.COFFEE, Item.DATE, Item.ALCHOLE, Item.TRANSPORTATION, Item.ETC_EXPENDITURE)),
    INCOME("수입", Arrays.asList(Item.SALARY, Item.BONUS, Item.CLOTHING_PAYMENT, Item.GIFT_CARD, Item.ETC_INCOME));
    //EMPTY("없음",Collections.EMPTY_LIST);

    private final String title;

    private final List<Item> itemList;

    /*public static LedgerDsc findByLedgerCode(String code) {
        return Arrays.stream(LedgerDsc.values())
                .filter(ledgerDsc -> ledgerDsc.hasLedgerDsc(code))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasLedgerDsc(String code) {
        return itemList.stream()
                .anyMatch(item -> item.equals(code));
    }*/

}

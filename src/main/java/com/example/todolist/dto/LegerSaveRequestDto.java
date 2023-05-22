package com.example.todolist.dto;

import com.example.todolist.domain.ledger.Item;
import com.example.todolist.domain.ledger.Ledger;
import com.example.todolist.domain.ledger.LedgerDsc;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName : com.example.todolist.dto
 * fileName : LegerSaveRequestDto
 * author : SHW
 * date : 2023-04-16
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-16   SHW     최초 생성
 */

@NoArgsConstructor
@Getter
public class LegerSaveRequestDto {

    private String userId;
    private String useDate;
    private LedgerDsc ledgerDsc;
    private Item item;
    private int amount;
    private String cntn;

    @Builder
    public LegerSaveRequestDto(String userId, String useDate, LedgerDsc ledgerDsc, Item item, int amount, String cntn) {
        this.userId = userId;
        this.useDate = useDate;
        this.ledgerDsc = ledgerDsc;
        this.item = item;
        this.amount = amount;
        this.cntn = cntn;
    }

    public Ledger toEntity() {
        return Ledger.builder()
                .userId(userId)
                .useDate(useDate)
                .ledgerDsc(ledgerDsc)
                .item(item)
                .amount(amount)
                .cntn(cntn)
                .build();
    }


}

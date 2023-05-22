package com.example.todolist.dto;

import com.example.todolist.domain.ledger.Item;
import com.example.todolist.domain.ledger.Ledger;
import com.example.todolist.domain.ledger.LedgerDsc;
import lombok.Getter;

/**
 * packageName : com.example.todolist.dto
 * fileName : LedgerListResponseDto
 * author : SHW
 * date : 2023-05-22
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-05-22   SHW     최초 생성
 */

@Getter
public class LedgerListResponseDto {

    private Long id;
    private String useDate;
    private LedgerDsc ledgerDsc;
    private Item item;
    private int amount;

    public LedgerListResponseDto(Ledger entity){
        this.id = entity.getId();
        this.useDate = entity.getUseDate();
        this.ledgerDsc = entity.getLedgerDsc();
        this.item = entity.getItem();
        this.amount = entity.getAmount();
    }

}

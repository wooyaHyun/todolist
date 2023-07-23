package com.example.todolist.controller.dto.ledger;

import com.example.todolist.domain.ledger.Item;
import com.example.todolist.domain.ledger.Ledger;
import com.example.todolist.domain.ledger.LedgerDsc;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName : com.example.todolist.dto.ledger
 * fileName : LedgerResponseDto
 * author : SHW
 * date : 2023-06-12
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-12   SHW     최초 생성
 */


@Getter
public class LedgerResponseDto {
    private Long id;
    private String userId;
    private String useDate;
    private String ledgerDsc;
    private String item;
    private int amount;
    private String cntn;

    public LedgerResponseDto(Ledger entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.useDate = entity.getUseDate();
        this.ledgerDsc = entity.getLedgerDsc().name();
        this.item = entity.getItem().name();
        this.amount = entity.getAmount();
        this.cntn = entity.getCntn();
    }
}

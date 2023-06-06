package com.example.todolist.dto.ledger;

import com.example.todolist.domain.ledger.Ledger;
import com.example.todolist.domain.ledger.LedgerDsc;
import lombok.Getter;

/**
 * packageName : com.example.todolist.dto.ledger
 * fileName : LedgerGroupSumResponseDto
 * author : SHW
 * date : 2023-06-06
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-06   SHW     최초 생성
 */

@Getter
public class LedgerGroupSumResponseDto {
    private String useDate;
    private String ledgerDsc;
    private Long amount;

    public LedgerGroupSumResponseDto(String userDate, LedgerDsc ledgerDsc, Long amount){
        this.useDate = userDate;
        this.ledgerDsc = ledgerDsc.getTitle();
        this.amount = amount;
    }
}

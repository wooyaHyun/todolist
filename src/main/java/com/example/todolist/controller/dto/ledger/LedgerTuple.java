package com.example.todolist.controller.dto.ledger;

import com.example.todolist.domain.ledger.LedgerDsc;
import lombok.AllArgsConstructor;

/**
 * packageName : com.example.todolist.dto.ledger
 * fileName : LedgerTuple
 * author : SHW
 * date : 2023-06-12
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-12   SHW     최초 생성
 */

@AllArgsConstructor
public class LedgerTuple {

    private String useDate;
    private String ledgerDsc;

    @Override
    public String toString() {
        return "LedgerTuple{" +
                "useDate='" + useDate + '\'' +
                ", ledgerDsc='" + ledgerDsc + '\'' +
                '}';
    }
}

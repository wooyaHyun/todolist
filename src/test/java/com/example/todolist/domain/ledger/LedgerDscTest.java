package com.example.todolist.domain.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : LedgerDscTest
 * author : SHW
 * date : 2023-06-03
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-03   SHW     최초 생성
 */

class LedgerDscTest {

    @Test
    void findByLedgerCodeTest() {
        System.out.println(LedgerDsc.findByLedgerCode(Item.ALCOHOL));

    }
}
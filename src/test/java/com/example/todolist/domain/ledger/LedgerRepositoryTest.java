package com.example.todolist.domain.ledger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : LedgerRepositoryTest
 * author : SHW
 * date : 2023-04-16
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-16   SHW     최초 생성
 */

@DataJpaTest
class LedgerRepositoryTest {

    @Autowired
    private LedgerRepository ledgerRepository;

    @Test
    void 장부래포_null이아님() {
        assertThat(ledgerRepository).isNotNull();

    }

    @Test
    void 장부등록_지출() {
        //given
        final Ledger ledger = Ledger.builder()
                .ledgerDsc(LedgerDsc.EXPENDITURE)
                .userId("test")
                .useDate("20230519")
                .item(Item.ALCHOLE)
                .amount(66000)
                .cntn("친구 올라옴")
                .build();

        //when
        ledgerRepository.save(ledger);
        final Ledger findLedger = ledgerRepository.findByLedgerDsc(LedgerDsc.EXPENDITURE);

        //then
        assertThat(findLedger).isNotNull();
        assertThat(findLedger.getAmount()).isEqualTo(66000);
        assertThat(findLedger.getLedgerDsc()).isEqualTo(LedgerDsc.EXPENDITURE);
        assertThat(findLedger.getItem()).isEqualTo(Item.ALCHOLE);

    }

}
package com.example.todolist.domain.ledger;

import com.example.todolist.dto.ledger.LedgerGroupSumResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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
                .item(Item.ALCOHOL)
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
        assertThat(findLedger.getItem()).isEqualTo(Item.ALCOHOL);

    }

    @Test
    void 장부일별합계() {
        //given
        final Ledger ledger1 = Ledger.builder()
                .ledgerDsc(LedgerDsc.EXPENDITURE)
                .userId("test")
                .useDate("20230519")
                .item(Item.ALCOHOL)
                .amount(66000)
                .cntn("친구 올라옴")
                .build();

        final Ledger ledger2 = Ledger.builder()
                .ledgerDsc(LedgerDsc.EXPENDITURE)
                .userId("test")
                .useDate("20230519")
                .item(Item.COFFEE)
                .amount(500)
                .cntn("친구 올라옴")
                .build();

        final Ledger ledger3 = Ledger.builder()
                .ledgerDsc(LedgerDsc.INCOME)
                .userId("test")
                .useDate("20230519")
                .item(Item.BONUS)
                .amount(2000)
                .cntn("친구 올라옴")
                .build();

        final Ledger ledger4= Ledger.builder()
                .ledgerDsc(LedgerDsc.INCOME)
                .userId("test")
                .useDate("20230519")
                .item(Item.GIFT_CARD)
                .amount(4000)
                .cntn("친구 올라옴")
                .build();

        //when
        ledgerRepository.save(ledger1);
        ledgerRepository.save(ledger2);
        ledgerRepository.save(ledger3);
        ledgerRepository.save(ledger4);

        List<LedgerGroupSumResponseDto> result = ledgerRepository.findByGroupingSum("test", "20230514", "20230520");

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);

        for (LedgerGroupSumResponseDto s : result) {
            System.out.println("s = " + s.getAmount());
        }

    }

}
package com.example.todolist.service.ledger;

import com.example.todolist.domain.ledger.*;
import com.example.todolist.dto.EnumMapperValue;
import com.example.todolist.dto.ledger.LedgerGroupSumResponseDto;
import com.example.todolist.dto.ledger.LedgerListResponseDto;
import com.example.todolist.dto.ledger.LedgerMainResponseDto;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * packageName : com.example.todolist.service.ledger
 * fileName : LedgerServiceTest
 * author : SHW
 * date : 2023-04-16
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-16   SHW     최초 생성
 */

@ExtendWith(MockitoExtension.class)
class LedgerServiceTest {

    @InjectMocks
    private LedgerService ledgerService;

    @Mock
    private LedgerRepository ledgerRepository;

    @Test
    void 장부_목록_조회() {
        //given
        when(ledgerRepository.findAllDesc("test", "20230501", "20230530")).thenReturn(
                Arrays.asList(
                        ledger(),
                        ledger(),
                        ledger()
                )
        );

        //given
        when(ledgerRepository.findByGroupingSum("test", "20230501", "20230530")).thenReturn(
                Arrays.asList(
                        new LedgerGroupSumResponseDto("20230501", LedgerDsc.EXPENDITURE, 500L),
                        new LedgerGroupSumResponseDto("20230501", LedgerDsc.INCOME, 200L)
                )
        );

        //when
        final LedgerMainResponseDto result = ledgerService.getLedgerList("test", "20230501", "20230530");

        //then
        assertThat(result.getLedgerListResponseDtoList().size()).isEqualTo(3);
        assertThat(result.getLedgerGroupSumResponseDtoList().size()).isEqualTo(2);
        assertThat(result.getLedgerGroupSumResponseDtoList().get(0).getAmount()).isEqualTo(500L);
    }


    /*
    @Test
    void 장부_구분및아이템_리스트_조회() {
        //given

        //when
        List<EnumMapperValue> legerDscList = ledgerService.getLegerDscList();

        //then
        //assertThat(legerDscList.size()).isEqualTo(12);

    }*/

    @Test
    void 장부등록성공() {
        //given
        when(ledgerRepository.save(any(Ledger.class))).thenReturn(ledger());
        //doReturn(ledger()).when(ledgerRepository).save(any(Ledger.class));

        //when
        final Long result = ledgerService.addLedger(LegerSaveRequestDto.builder().build());

        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(-1L);


    }



    private Ledger ledger(){
        return Ledger.builder()
                .id(-1L)
                .userId("test")
                .useDate("20230517")
                .ledgerDsc(LedgerDsc.EXPENDITURE)
                .item(Item.ALCOHOL)
                .amount(66000)
                .cntn("성영이 올라옴")
                .build();
    }

}
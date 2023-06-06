package com.example.todolist.service.ledger;

import com.example.todolist.domain.ledger.Ledger;
import com.example.todolist.domain.ledger.LedgerDsc;
import com.example.todolist.domain.ledger.LedgerRepository;
import com.example.todolist.dto.EnumMapperValue;
import com.example.todolist.dto.ledger.LedgerGroupSumResponseDto;
import com.example.todolist.dto.ledger.LedgerListResponseDto;
import com.example.todolist.dto.ledger.LedgerMainResponseDto;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * packageName : com.example.todolist.service.ledger
 * fileName : LedgerService
 * author : SHW
 * date : 2023-04-16
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-16   SHW     최초 생성
 */


@RequiredArgsConstructor
@Service
public class LedgerService {
    private final LedgerRepository ledgerRepository;

    @Transactional
    public Long save(LegerSaveRequestDto requestDto) {

        return ledgerRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional(readOnly = true)
    public List<EnumMapperValue> getLegerDscList() {

        //return Stream.of(LedgerDsc.values()).collect(Collectors.toList());
        return Stream.of(LedgerDsc.values())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LedgerMainResponseDto getLedgerList(final String userId, final String fromDate, final String toDate) {
        List<LedgerListResponseDto> ledgerList = ledgerRepository.findAllDesc(userId, fromDate, toDate).stream().map(LedgerListResponseDto::new).collect(Collectors.toList());
        List<LedgerGroupSumResponseDto> byGroupingSum = ledgerRepository.findByGroupingSum(userId, fromDate, toDate);

        return LedgerMainResponseDto.builder()
                .ledgerListResponseDtoList(ledgerList)
                .ledgerGroupSumResponseDtoList(byGroupingSum)
                .build();
    }

}

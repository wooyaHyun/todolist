package com.example.todolist.service.ledger;

import com.example.todolist.domain.ledger.LedgerDsc;
import com.example.todolist.domain.ledger.LedgerRepository;
import com.example.todolist.dto.LedgerListResponseDto;
import com.example.todolist.dto.LegerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<LedgerDsc> getLegerDscList() {

        return Stream.of(LedgerDsc.values()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LedgerListResponseDto> getLedgerList(final String userId, final String fromDate, final String toDate) {
        return ledgerRepository.findAllDesc(userId, fromDate, toDate).stream().map(LedgerListResponseDto::new).collect(Collectors.toList());
    }

}

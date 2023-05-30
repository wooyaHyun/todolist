package com.example.todolist.controller;

import com.example.todolist.dto.EnumMapperValue2;
import com.example.todolist.dto.LedgerListResponseDto;
import com.example.todolist.dto.LegerSaveRequestDto;
import com.example.todolist.service.ledger.LedgerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.todolist.controller
 * fileName : LedgerController
 * author : SHW
 * date : 2023-05-17
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-05-17   SHW     최초 생성
 */

@RequiredArgsConstructor
@RestController
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping("/api/v1/ledgers")
    public ResponseEntity<List<LedgerListResponseDto>> getLedgerList(
            @RequestParam("userId") String userId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

        return ResponseEntity.ok(ledgerService.getLedgerList(userId, fromDate, toDate));
    }

    @PostMapping("/api/v1/ledgers")
    public ResponseEntity<Long> addLedger(@RequestBody @Valid LegerSaveRequestDto requestDto) {
        Long save = ledgerService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping("/api/v1/ledger-dsc")
    public ResponseEntity<List<EnumMapperValue2>> getLedgerDscList() {

        return ResponseEntity.ok(ledgerService.getLegerDscList());
    }
}

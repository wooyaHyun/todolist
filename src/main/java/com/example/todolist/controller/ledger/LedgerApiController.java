package com.example.todolist.controller.ledger;

import com.example.todolist.config.EnumMapper;
import com.example.todolist.dto.EnumMapperValue;
import com.example.todolist.dto.ledger.LedgerMainResponseDto;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import com.example.todolist.service.ledger.LedgerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
public class LedgerApiController {

    private final LedgerService ledgerService;
    private final EnumMapper enumMapper;

    @GetMapping("/api/v1/ledgers")
    public ResponseEntity<LedgerMainResponseDto> getLedgerList(
            @RequestParam("userId") String userId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

        return ResponseEntity.ok(ledgerService.getLedgerList(userId, fromDate, toDate));
    }

    @PostMapping("/api/v1/ledgers")
    public ResponseEntity<Long> addLedger(@RequestBody @Valid LegerSaveRequestDto requestDto) {
        Long save = ledgerService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping("/api/v1/ledger-dsc")
    public ResponseEntity<Map<String, List<EnumMapperValue>>> getLedgerDscList() {
        //return ResponseEntity.ok(ledgerService.getLegerDscList());
        return ResponseEntity.ok(enumMapper.getAll());
    }
}

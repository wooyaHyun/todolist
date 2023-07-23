package com.example.todolist.controller.ledger;

import com.example.todolist.controller.dto.EnumMapper;
import com.example.todolist.controller.dto.EnumMapperValue;
import com.example.todolist.controller.dto.ledger.LedgerMainResponseDto;
import com.example.todolist.controller.dto.ledger.LegerSaveRequestDto;
import com.example.todolist.service.ledger.LedgerService;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession httpSession;

    @GetMapping("/api/v1/ledgers")
    public ResponseEntity<LedgerMainResponseDto> getLedgerList(
            @RequestParam("userId") String userId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

        return ResponseEntity.ok(ledgerService.getLedgerList(userId, fromDate, toDate));

    }

    @PostMapping("/api/v1/ledgers")
    public ResponseEntity<Long> addLedger(@RequestBody @Valid LegerSaveRequestDto requestDto) {
        Long save = ledgerService.addLedger(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);

    }

    @GetMapping("/api/v1/ledger-dsc")
    public ResponseEntity<Map<String, List<EnumMapperValue>>> getLedgerDscList() {
        //return ResponseEntity.ok(ledgerService.getLegerDscList());
        return ResponseEntity.ok(enumMapper.getAll());
    }

    @PutMapping("/api/v1/ledgers/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody LegerSaveRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ledgerService.update(id, requestDto));
    }

    @DeleteMapping("/api/v1/ledgers/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ledgerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.todolist.controller.ledger;

import com.example.todolist.config.auth.dto.LoginUser;
import com.example.todolist.config.auth.dto.SessionUser;
import com.example.todolist.dto.EnumMapper;
import com.example.todolist.dto.EnumMapperValue;
import com.example.todolist.dto.ledger.LedgerMainResponseDto;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import com.example.todolist.service.ledger.LedgerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
            @LoginUser SessionUser user, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
        if(user != null){
            return ResponseEntity.ok(ledgerService.getLedgerList(user.getUserId(), fromDate, toDate));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/api/v1/ledgers")
    public ResponseEntity<Long> addLedger(@LoginUser SessionUser user, @RequestBody @Valid LegerSaveRequestDto requestDto) {
        Long save = ledgerService.save(user.getUserId(), requestDto);

        if(user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        }

        return ResponseEntity.badRequest().build();
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

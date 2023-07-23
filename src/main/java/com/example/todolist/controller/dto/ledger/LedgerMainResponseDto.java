package com.example.todolist.controller.dto.ledger;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * packageName : com.example.todolist.dto
 * fileName : LedgerMainResponseDto
 * author : SHW
 * date : 2023-06-06
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-06   SHW     최초 생성
 */

@Builder
@Getter
public class LedgerMainResponseDto {
    private final List<LedgerListResponseDto> ledgerListResponseDtoList;
    private final List<LedgerGroupSumResponseDto> ledgerGroupSumResponseDtoList;

}

package com.example.todolist.controller;

import com.example.todolist.domain.ledger.Item;
import com.example.todolist.domain.ledger.Ledger;
import com.example.todolist.domain.ledger.LedgerDsc;
import com.example.todolist.dto.LedgerListResponseDto;
import com.example.todolist.dto.LegerSaveRequestDto;
import com.example.todolist.service.ledger.LedgerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName : com.example.todolist.controller
 * fileName : LedgerControllerTest
 * author : SHW
 * date : 2023-05-17
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-05-17   SHW     최초 생성
 */

@ExtendWith(MockitoExtension.class)
class LedgerControllerTest {

    @InjectMocks
    private LedgerController ledgerController;

    @Mock
    private LedgerService ledgerService;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(ledgerController)
                .build();
    }

    @Test
    void 장부_리스트조회_성공() throws Exception {
        //given
        final String url = "/api/v1/ledgers";
        when(ledgerService.getLedgerList("test", "20230501", "20230530")).thenReturn(
                Arrays.asList(
                        new LedgerListResponseDto(ledger()),
                        new LedgerListResponseDto(ledger()),
                        new LedgerListResponseDto(ledger())
                )
        );

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "test")
                        .param("fromDate", "20230501")
                        .param("toDate", "20230530")

        );

        //then
        resultActions.andExpect(status().isOk());

    }

    @Test
    void 장부_구분및항목_리스트조회_성공() throws Exception {
        //given
        final String url = "/api/v1/ledger-dsc";

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        );

        //then
        resultActions.andExpect(status().isOk());

    }

    @Test
    void 장부_등록_성공() throws Exception {

        //given
        final String url = "/api/v1/ledgers";

        LegerSaveRequestDto requestDto = LegerSaveRequestDto.builder()
                .useDate("20230517")
                .ledgerDsc(LedgerDsc.EXPENDITURE)
                .item(Item.ALCHOLE)
                .amount(66000)
                .cntn("성영이 올라옴")
                .build();

        when(ledgerService.save(any(LegerSaveRequestDto.class))).thenReturn(-1L);

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto))
        );

        //then
        resultActions.andExpect(status().isCreated());

    }

    private Ledger ledger(){
        return Ledger.builder()
                .id(-1L)
                .userId("test")
                .useDate("20230517")
                .ledgerDsc(LedgerDsc.EXPENDITURE)
                .item(Item.ALCHOLE)
                .amount(66000)
                .cntn("성영이 올라옴")
                .build();
    }
}
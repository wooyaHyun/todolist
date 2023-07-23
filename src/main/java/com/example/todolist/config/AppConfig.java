package com.example.todolist.config;

import com.example.todolist.domain.ledger.LedgerDsc;
import com.example.todolist.controller.dto.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName : com.example.todolist.config
 * fileName : AppConfig
 * author : SHW
 * date : 2023-06-03
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-03   SHW     최초 생성
 */

@Configuration
public class AppConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();

        enumMapper.put("ledgerDsc", LedgerDsc.class);

        enumMapper.put("expenditureItem", LedgerDsc.findByExpenditureItemList());
        enumMapper.put("incomeItem", LedgerDsc.findByIncomeItemList());

        return enumMapper;
    }

}

package com.example.todolist.service.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName : com.example.todolist.service.facade
 * fileName : OrderService
 * author : SHW
 * date : 2023-07-24
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-24   SHW     최초 생성
 */

@Slf4j
@Service
public class OrderService {
    public void orderCancel(){
        log.info("주문 취소");
    }
}

package com.example.todolist.service.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName : com.example.todolist.service.facade
 * fileName : PointService
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
public class PointService {

    public void pointCancel(){
        log.info("포인트 취소");
    }

}

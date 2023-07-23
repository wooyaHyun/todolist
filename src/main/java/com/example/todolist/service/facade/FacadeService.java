package com.example.todolist.service.facade;

import org.springframework.stereotype.Component;

/**
 * packageName : com.example.todolist.service.facade
 * fileName : FacadeService
 * author : SHW
 * date : 2023-07-24
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-24   SHW     최초 생성
 */

@Component
public class FacadeService {

    private final OrderService orderService;
    private final UmsService kakaotalkService;
    private final PointService pointService;

    public FacadeService(OrderService orderService, UmsService kakaotalkService, PointService pointService) {
        this.orderService = orderService;
        this.kakaotalkService = kakaotalkService;
        this.pointService = pointService;
    }

    public void cancelProcess(){
        orderService.orderCancel();
        pointService.pointCancel();
        kakaotalkService.kakaoNoticePush();
    }
}

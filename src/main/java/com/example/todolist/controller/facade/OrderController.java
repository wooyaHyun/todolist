package com.example.todolist.controller.facade;

import com.example.todolist.service.facade.FacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.example.todolist.controller.facade
 * fileName : OrderController
 * author : SHW
 * date : 2023-07-24
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-24   SHW     최초 생성
 */

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final FacadeService facadeService;

    @GetMapping("/order-cancel")
    public void cancelOrder(){
        facadeService.cancelProcess();
    }


}

package com.example.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.example.todolist.controller
 * fileName : IndexController
 * author : SHW
 * date : 2023-05-22
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-05-22   SHW     최초 생성
 */


@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/ledgers")
    public String ledgers(){
        return "ledger/ledger";
    }

    @GetMapping("/ledgers/save")
    public String ledgersSave(){
        return "ledger/ledgers-save";
    }

}

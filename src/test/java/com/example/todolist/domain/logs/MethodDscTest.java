package com.example.todolist.domain.logs;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.todolist.domain.logs
 * fileName : MethodDscTest
 * author : SHW
 * date : 2023-06-22
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-22   SHW     최초 생성
 */

class MethodDscTest {

    @Test
    void findByMethodDsc() {
        //given
        String param1 = "addLedger";
        String param2 = "updateTest";
        String param3 = "deleteTest";
        String param4 = "getTest";


        //when
        MethodDsc result1 = MethodDsc.findByMethodDsc(param1);
        MethodDsc result2 = MethodDsc.findByMethodDsc(param2);
        MethodDsc result3 = MethodDsc.findByMethodDsc(param3);
        MethodDsc result4 = MethodDsc.findByMethodDsc(param4);


        //thenb
        assertThat(result1.name()).isEqualTo("CREATE");
        assertThat(result2.name()).isEqualTo("UPDATE");
        assertThat(result3.name()).isEqualTo("DELETE");
        assertThat(result4.name()).isEqualTo("READ");



    }
}
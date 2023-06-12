package com.example.todolist.config;

import com.example.todolist.dto.EnumMapper;
import com.example.todolist.dto.EnumMapperValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : com.example.todolist.config
 * fileName : EnumMapperTest
 * author : SHW
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-07   SHW     최초 생성
 */
@SpringBootTest
class EnumMapperTest {

    @Autowired
    private EnumMapper enumMapper;

    @Test
    void enumMapper_null이아님() {
        assertThat(enumMapper).isNotNull();

    }

    @Test
    void enum값_확인() {
        //given


        //when
        Map<String, List<EnumMapperValue>> stringListMap = enumMapper.get(Arrays.asList("ledgerDsc", "incomeItem"));

        //then
        assertThat(stringListMap.get("ledgerDsc").size()).isEqualTo(2);
        assertThat(stringListMap.get("ledgerDsc").get(0).getTitle()).isEqualTo("지출"); 
        assertThat(stringListMap.get("incomeItem").size()).isEqualTo(5);
        assertThat(stringListMap.get("incomeItem").get(0).getTitle()).isEqualTo("월급");

    }

}
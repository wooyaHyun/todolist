package com.example.todolist.dto;

import com.example.todolist.dto.EnumMapperType;
import com.example.todolist.dto.EnumMapperValue;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * packageName : com.example.todolist.dto
 * fileName : EnumMapper
 * author : SHW
 * date : 2023-06-03
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-03   SHW     최초 생성
 */

public class EnumMapper {
    private Map<String, List<EnumMapperValue>> factory = new LinkedHashMap<>();

    public EnumMapper() {}

    public void put(String key, Class<? extends EnumMapperType> e){
        factory.put(key, toEnumValues(e));
    }

    public void put(String key, List<EnumMapperValue> e){
        factory.put(key, e);
    }

    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> e) {

        return Arrays.stream(e.getEnumConstants())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

    public List<EnumMapperValue> get(String key){
        return factory.get(key);
    }

    public Map<String, List<EnumMapperValue>> get(List<String> keys){
        if (keys == null || keys.size() == 0) {
            return new LinkedHashMap<>();
        }

        return keys.stream().collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));

    }

    public Map<String, List<EnumMapperValue>> getAll(){return factory;}


}

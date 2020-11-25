package com.example.petproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {

    private String name;
    private Map<String, String> functionMap;

}

package com.example.petproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ValidCodeBean implements Serializable {

    private String code;
    private LocalDateTime expireTime;
}

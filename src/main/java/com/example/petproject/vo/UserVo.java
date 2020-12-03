package com.example.petproject.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserVo {

    private String account;
    private String name;
    private String gender;
    private String email;
    private String level;
    private LocalDate createDate;
}

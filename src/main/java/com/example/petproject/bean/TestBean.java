package com.example.petproject.bean;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Locale;

@Data
public class TestBean {

    @NotBlank(message = "姓名不得為空")
    private String name;
    @Min(value = 18, message = "未成年唷")
    private int age;

}

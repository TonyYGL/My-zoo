package com.example.petproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerException extends RuntimeException{

    private int errorCode;
    private String erroMessage;

}

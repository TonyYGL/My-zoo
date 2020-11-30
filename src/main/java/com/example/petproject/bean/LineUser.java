package com.example.petproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LineUser implements Serializable {

    private String name;
    private String picture;
}

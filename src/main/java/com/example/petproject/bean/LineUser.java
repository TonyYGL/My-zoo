package com.example.petproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LineUser implements Serializable {
    private static final long serialVersionUID = -443328237735573456L;
    private String name;
    private String picture;
    private String email;
}

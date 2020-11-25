package com.example.petproject.po;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "user")
public class UserPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String account;
    private String password;
    private String name;
    private int genderId;
    private String email;
    private int level;
    private LocalDate createDate;
}

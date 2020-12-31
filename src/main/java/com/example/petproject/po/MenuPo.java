package com.example.petproject.po;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "menu")
public class MenuPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int status = 1; // 0關閉 1開放
    private int level = 2; // 1:管理員 2:普通
    private String url;
    private String icon;
}

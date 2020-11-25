package com.example.petproject.po;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "menu_detail")
public class MenuDetailPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int menuId;
    private String name;
    private int status = 1; // 0關閉 1開放
    private String url;
    private int level = 2; // 1:管理員 2:普通

}

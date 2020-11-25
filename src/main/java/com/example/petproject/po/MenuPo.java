package com.example.petproject.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "menu")
public class MenuPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "menuId")
    private Set<MenuDetailPo> menuDetailPos;

    private String name;
    private int status = 1; // 0關閉 1開放
    private int level = 2; // 1:管理員 2:普通
}

package com.example.petproject.po;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pet")
@Data
public class PetPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "DATE")
    private LocalDate birthday;

    private int typeId;
    private String name;
    private String description;
    private int genderId;
    private int ownerId;

}

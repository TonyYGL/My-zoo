package com.example.petproject.po;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "image")
public class ImagePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;
    private String uuid;
    private String fileName;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;
}

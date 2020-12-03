package com.example.petproject.vo;

import lombok.Data;

@Data
public class AdoptVo {

    private String id;
    private String age;
    private String kind;
    private String color;
    private String sex;
    private String place;
    private String bodyType;
    private String albumFile;
    private String shelterPhone;
    private String shelterAddress;
    private String remark;
    // 是否絕育
    private String sterilization;
    // 是否施打狂犬病疫苗
    private String bacterin;
    private String foundPlace;
    private String status;
    private String openDate;


}

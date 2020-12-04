package com.example.petproject.enums;

import java.util.Arrays;

public enum GenderType {

    UNKNOWN(0, "未知性別"), MALE(1, "男生"), FEMALE(2, "女生");

    private int genderId;
    private String gender;

    GenderType(int genderId, String gender) {
        this.genderId = genderId;
        this.gender = gender;
    }

    public static String getGender(int genderId) {
        return Arrays.stream(GenderType.values())
                .filter(genderType -> genderType.genderId == genderId)
                .findFirst()
                .orElse(UNKNOWN).gender;
    }
}

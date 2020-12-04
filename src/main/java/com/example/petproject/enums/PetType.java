package com.example.petproject.enums;

import java.util.Arrays;

public enum PetType {

    UNKNOWN(0, "未知生物"), CAT(1, "貓貓"), RABBIT(2, "兔兔"), DOG(3, "狗狗");

    private int typeId;
    private String type;

    PetType(int typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public static String getType(int typeId) {
        return Arrays.stream(PetType.values())
                .filter(petType -> petType.typeId == typeId)
                .findFirst()
                .orElse(UNKNOWN)
                .type;
    }
}

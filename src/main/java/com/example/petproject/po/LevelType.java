package com.example.petproject.po;

import java.util.Arrays;

public enum LevelType {

    ADMIN(1, "管理員"), MEMBER(2, "一般會員"), VISITOR(3, "訪客");

    private int levelId;
    private String levelName;

    private LevelType(int levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    public static String getType(int levelId) {
        return Arrays.stream(LevelType.values())
                .filter(petType -> petType.levelId == levelId)
                .findFirst()
                .orElse(VISITOR)
                .levelName;
    }
}

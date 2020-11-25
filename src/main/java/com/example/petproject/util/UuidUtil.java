package com.example.petproject.util;

import java.util.UUID;

public class UuidUtil {

    public static final String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}

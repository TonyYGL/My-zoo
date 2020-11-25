package com.example.petproject.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PetVo {
    @NonNull
    private String name;
    @NonNull
    private String gender;

    @NonNull
    private LocalDate birthday;
}

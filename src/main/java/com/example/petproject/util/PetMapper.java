package com.example.petproject.util;

import com.example.petproject.po.PetPo;
import com.example.petproject.vo.PetVo;

public interface PetMapper {
    PetVo petPoToVo(PetPo petPo);
}

package com.example.petproject.util;

import com.example.petproject.po.GenderType;
import com.example.petproject.po.PetPo;
import com.example.petproject.vo.PetVo;
import org.springframework.stereotype.Component;

@Component
public class PetMapperImpl implements PetMapper {
    @Override
    public PetVo petPoToVo(PetPo petPo) {
        if (petPo == null) {
            throw new RuntimeException("Pet po to vo : po is null");
        }
        PetVo petVo = new PetVo();
        petVo.setName(petPo.getName());
        petVo.setGender(GenderType.getGender(petPo.getGenderId()));
        petVo.setBirthday(petPo.getBirthday());
        return petVo;
    }
}

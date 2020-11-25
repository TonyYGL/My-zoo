package com.example.petproject.service;

import com.example.petproject.util.PetMapper;
import com.example.petproject.po.PetPo;
import com.example.petproject.repository.PetRepository;
import com.example.petproject.vo.PetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;
    public List<PetVo> findByOwnerId(int ownerId) {
        List<PetPo> petPoList = petRepository.findByOwnerId(ownerId);
        List<PetVo> petVoList = new ArrayList<>();
        for (PetPo petPo : petPoList) {
            petVoList.add(petMapper.petPoToVo(petPo));
        }
        return petVoList;
    }
}

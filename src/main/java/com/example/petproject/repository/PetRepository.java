package com.example.petproject.repository;

import com.example.petproject.po.PetPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetPo, Long> {
    public List<PetPo> findByOwnerId(int ownerId);
}

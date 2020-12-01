package com.example.petproject.repository;

import com.example.petproject.po.ImagePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImagePo, Long> {
    public List<ImagePo> findAllByUserId(long userId);
}

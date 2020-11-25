package com.example.petproject.repository;

import com.example.petproject.po.MenuDetailPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDetailRepository extends JpaRepository<MenuDetailPo, Long> {
    List<MenuDetailPo> findByLevel(int level);
}

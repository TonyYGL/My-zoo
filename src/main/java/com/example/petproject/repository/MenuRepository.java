package com.example.petproject.repository;

import com.example.petproject.po.MenuPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends JpaRepository<MenuPo, Long> {
}

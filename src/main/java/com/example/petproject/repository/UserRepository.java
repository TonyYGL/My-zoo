package com.example.petproject.repository;

import com.example.petproject.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPo, Long> {
    public UserPo findByAccountAndPassword(String name, String password);
}

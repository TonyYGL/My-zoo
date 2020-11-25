package com.example.petproject.service;

import com.example.petproject.po.UserPo;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.util.UserMapper;
import com.example.petproject.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserVo findUserInfo(String account, String password) {
        UserPo userPo = userRepository.findByAccountAndPassword(account, password);
        return userMapper.userPoToVo(userPo);
    }

}

package com.example.petproject.service;

import com.example.petproject.po.UserPo;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.util.PasswordUtil;
import com.example.petproject.util.UserMapper;
import com.example.petproject.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtil passwordUtil;

    public UserVo findById(long id) {
        UserPo userPo = userRepository.findById(id).orElse(null);
        return userMapper.userPoToVo(userPo);
    }

    public UserVo findUserInfo(String account, String password) {
        UserPo userPo = userRepository.findByAccountAndPassword(account, password);
        return userMapper.userPoToVo(userPo);
    }

    public boolean changePassword(String password, String passwordValidation) {
        boolean result = false;
        if (password.equals(passwordValidation)) {
            Optional<UserPo> optionalUserPo = userRepository.findById(1L);
            if (optionalUserPo.isPresent()) {
                UserPo userPo = optionalUserPo.get();
                userPo.setPassword(passwordUtil.generateSecurePassword(password));
                userRepository.save(userPo);
                result = true;
            }
        }
        return result;
    }

}

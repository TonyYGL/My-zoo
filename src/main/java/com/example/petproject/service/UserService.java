package com.example.petproject.service;

import com.example.petproject.bean.LoginForm;
import com.example.petproject.po.UserPo;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.util.PasswordUtil;
import com.example.petproject.util.RedisUtil;
import com.example.petproject.util.UserMapper;
import com.example.petproject.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RedisUtil redisUtil;

    public void registerUser(LoginForm loginForm) {
        String key = httpSession.getId() + "_info";
        redisUtil.putHash(key, "account", loginForm.getAccount());
        redisUtil.putHash(key, "password", passwordUtil.generateSecurePassword(loginForm.getPassword()));
        redisUtil.putHash(key, "mail", loginForm.getMail());
        redisUtil.putHash(key, "gender", loginForm.getGender());
        redisUtil.putHash(key, "name", loginForm.getName());
    }

    public void insertUser() {
        String key = httpSession.getId() + "_info";
        String account = redisUtil.getHash(key, "account").toString();
        String password = redisUtil.getHash(key, "password").toString();
        String mail = redisUtil.getHash(key, "mail").toString();
        int gender = Integer.valueOf(redisUtil.getHash(key, "gender").toString());
        String name = redisUtil.getHash(key, "name").toString();

        UserPo userPo = new UserPo();
        userPo.setAccount(account);
        userPo.setEmail(mail);
        userPo.setPassword(password);
        userPo.setGenderId(gender);
        userPo.setName(name);
        userPo.setCreateDate(LocalDate.now());
        userPo.setLevel(1);
        userRepository.save(userPo);
    }

    public Optional<UserVo> findById(long id) {
        UserPo userPo = userRepository.findById(id).orElse(null);
        return userMapper.userPoToVo(userPo);
    }

    public Optional<UserVo> findUserInfo(String account, String password) {
        String securePassword = passwordUtil.generateSecurePassword(password);
        UserPo userPo = userRepository.findByAccountAndPassword(account, securePassword);
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

package com.example.petproject.service;

import com.example.petproject.util.RedisUtil;
import com.example.petproject.util.StringRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class ValidCodeService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StringRedisUtil stringRedisUtil;

    @Autowired
    private UserService userService;

    public String validCode(String inputCode) {
        String result = "驗證成功";
        Optional<String> stringOptional = stringRedisUtil.getValueByKey(httpSession.getId() + "_validCode");
        if(stringOptional.isPresent()) {
           String code = stringOptional.get();
           if (code.equals(inputCode)) {
               // 驗證成功進db remove redis
               userService.insertUser();
           } else {
               result = "驗證失敗";
           }
        } else {
            result = "驗證碼過期，請重新獲取驗證碼";
        }
        return result;
    }
}

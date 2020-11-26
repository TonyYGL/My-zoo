package com.example.petproject.service;

import com.example.petproject.bean.ValidCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class ValidCodeService {

    @Autowired
    private HttpSession httpSession;

    public String validCode(String inputCode) {
        String result = "驗證成功";
        ValidCodeBean validCodeBean = (ValidCodeBean) httpSession.getAttribute("code");
        System.out.println("validCodeBean = " + validCodeBean);
        if (validCodeBean.getExpireTime().isBefore(LocalDateTime.now())) {
            System.out.println("驗證碼過期");
            result = "驗證碼過期，請重新獲取驗證碼";
        } else {
            if (!validCodeBean.getCode().equals(inputCode)) {
                System.out.println("code : " + validCodeBean.getCode() + " , input : " + inputCode);
                result = "驗證失敗";
            }
        }
        System.out.println("result = " + result);
        return result;
    }
}

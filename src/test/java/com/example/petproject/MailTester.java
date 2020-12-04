package com.example.petproject;

import com.example.petproject.service.MailService;
import com.example.petproject.service.ShelterService;
import com.example.petproject.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
public class MailTester {

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private ShelterService shelterService;


    @Test
    public void testMail() {
        mailService.sendValidCodeMail("pox810324@gmail.con");
    }

    @Test
    public void testEncode() {
        String password = "test123.@";
        String securePassword = passwordUtil.generateSecurePassword(password);
        System.out.println("securePassword = " + securePassword);
        boolean eqaul = passwordUtil.verifyUserPassword(password, securePassword);
        System.out.println("eqaul = " + eqaul);
    }

    @Test
    public void testShelter() {
        System.out.println(shelterService.getShelterList("E"));
    }
}

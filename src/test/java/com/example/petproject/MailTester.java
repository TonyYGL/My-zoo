package com.example.petproject;

import com.example.petproject.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
public class MailTester {

    @Autowired
    private MailService mailService;

    @Test
    public void testMail() throws MessagingException {
        mailService.sendValidCodeMail("pox810324@gmail.con");
    }
}

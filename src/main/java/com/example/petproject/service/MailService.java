package com.example.petproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public static final String CODE = "0123456789";

    public void sendValidCodeMail(String mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("pox810324@gmail.com");
            helper.setTo(mail);
            helper.setSubject("My Zoo 修改密碼通知信");

            String content = "<html><body><p>請於五分鐘內驗證此驗證碼</p><h4>code</h4><small>提醒您：此為系統發送信函請勿直接回覆此信。</small></body></html>";
            content = content.replaceFirst("code", getRandomCode());
            helper.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getRandomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6 ; i++) {
            int index = (int) (random.nextFloat() * CODE.length());
            stringBuilder.append(CODE.charAt(index));
        }
        return stringBuilder.toString();
    }


}

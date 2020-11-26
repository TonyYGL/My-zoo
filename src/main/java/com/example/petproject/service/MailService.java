package com.example.petproject.service;

import com.example.petproject.bean.ValidCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HttpSession httpSession;

    public static final String CODE = "0123456789";

    public void sendValidCodeMail(String mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("pox810324@gmail.com");
            helper.setTo(mail);
            helper.setSubject("My Zoo 修改密碼通知信");

            String content = "<html><body><p>請於五分鐘內驗證此驗證碼</p><h4>code</h4><small>提醒您：此為系統發送信函請勿直接回覆此信。</small></body></html>";
            String code = getRandomCode();
            setValidCode(code);
            content = content.replaceFirst("code", code);
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

    private void setValidCode(String code) {
        httpSession.setAttribute("code", new ValidCodeBean(code, LocalDateTime.now().plusMinutes(5)));
    }


}

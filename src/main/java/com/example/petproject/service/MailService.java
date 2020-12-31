package com.example.petproject.service;

import com.example.petproject.util.StringRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StringRedisUtil stringRedisUtil;

    public static final String CODE = "0123456789";

    public void sendValidCodeMail(String mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("pox810324@gmail.com");
            helper.setTo(mail);
            helper.setSubject("歡迎加入會員!!!");

            String content = "<html><body><p>此驗證碼將於五分鐘失效</p><h4>code</h4><small>提醒您：此為系統發送信函請勿直接回覆此信。</small></body></html>";
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
        stringRedisUtil.setValueWithTTL(httpSession.getId() + "_validCode", code, 5, TimeUnit.MINUTES);
//        httpSession.setAttribute("code", new ValidCodeBean(code, LocalDateTime.now().plusMinutes(5)));
    }


}

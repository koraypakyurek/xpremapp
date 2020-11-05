package com.xprem.support.business.impl;

import com.xprem.support.business.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    public final JavaMailSender javaMailSender;
    @Value("${email.sender.mail}")
    private String emailSenderMailAdress;

    @Value("${email.sender.info}")
    private String emailSenderInfo;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    @Override
    public void sendMail(String userMail) {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(userMail);
            messageHelper.setSubject("Premium üyelik işlemleri");
            messageHelper.setText("Premium üyeliğiniz başarı ile tamamlanmıştır.", true);
            messageHelper.setFrom(new InternetAddress(this.emailSenderMailAdress, this.emailSenderInfo));
            this.javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Mail Gonderilemedi : " + e.getMessage());
        } catch (
                UnsupportedEncodingException e) {
            logger.error("Mail Gonderilemedi : " + e.getMessage());
        }
    }
}

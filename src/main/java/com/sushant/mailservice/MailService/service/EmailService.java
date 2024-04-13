package com.sushant.mailservice.MailService.service;

import com.sushant.mailservice.MailService.dto.EMailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmail(EMailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(request.getToEmail());
        mailMessage.setSubject(request.getEmailSubject());
        mailMessage.setText(request.getEmailBody());

        try {
            javaMailSender.send(mailMessage);
            return "Email successfully sent to " + request.getToEmail();
        } catch (MailException ex) {
            logger.error("Error sending email to " + request.getToEmail(), ex);
            return "Failed to send email to " + request.getToEmail() + ": " + ex.getMessage();
        }
    }
}

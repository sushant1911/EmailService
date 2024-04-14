package com.example.TaskManager.service;
import com.example.TaskManager.dto.TaskResponseDTO;
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

    @Autowired(required=true)
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmail(TaskResponseDTO task) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo("sushantsuman1911@gmail.com");
        mailMessage.setSubject(task.toString());
        mailMessage.setText(task.toString());

        try {
            javaMailSender.send(mailMessage);
            return "Email successfully sent to " + task+"sushantsuman1911@gmail.com";
        } catch (MailException ex) {
            logger.error("Error sending email to " + task.toString(), ex);
            return "Failed to send email to " + task.toString()+ ": " + ex.getMessage();
        }
    }
}

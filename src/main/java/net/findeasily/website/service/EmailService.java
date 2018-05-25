package net.findeasily.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    private final Configuration ftlConf;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, Configuration ftlConf) {
        this.javaMailSender = javaMailSender;
        this.ftlConf = ftlConf;
    }

    public void sendMail(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("contact@mail.findeasily.net");
        javaMailSender.send(mailMessage);
    }
}

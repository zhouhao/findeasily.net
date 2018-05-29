package net.findeasily.website.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    public void sendMail(@NotNull SimpleMailMessage mailMessage) {
        javaMailSender.send(mailMessage);
    }


    public void sendHtmlMail(String toEmail, String subject, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setFrom("no-rely@mail.findeasily.net");
        // use the true flag to indicate the text included is HTML
        helper.setText(content, true);
        javaMailSender.send(message);
    }

}

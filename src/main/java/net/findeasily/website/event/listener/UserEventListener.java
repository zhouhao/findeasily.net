package net.findeasily.website.event.listener;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.Token;
import net.findeasily.website.domain.User;
import net.findeasily.website.event.UserEvent;
import net.findeasily.website.service.EmailService;
import net.findeasily.website.service.TokenService;

@Component
@Slf4j
public class UserEventListener {
    private final TokenService tokenService;
    private final EmailService emailService;
    private final Configuration freemarkerConfig;

    @Autowired
    public UserEventListener(EmailService emailService, TokenService tokenService, Configuration freemarkerConfig) {
        this.emailService = emailService;
        this.tokenService = tokenService;
        this.freemarkerConfig = freemarkerConfig;
    }

    @EventListener
    public void handleEvent(UserEvent event) throws IOException, TemplateException {
        log.info(event.getType() + " - " + event.getUser());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(event.getUser().getEmail());
        mailMessage.setSubject(event.getType().getSubject());
        mailMessage.setText(buildEmailContent(event));
        mailMessage.setFrom("no-rely@mail.findeasily.net");
        emailService.sendMail(mailMessage);
    }

    private String buildEmailContent(UserEvent event) throws IOException, TemplateException {
        UserEvent.Type type = event.getType();
        User user = event.getUser();
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
        Template t = freemarkerConfig.getTemplate(type.getTemplateFile());
        return FreeMarkerTemplateUtils.processTemplateIntoString(t, buildModel(type, user));
    }

    private Map<String, Object> buildModel(UserEvent.Type type, User user) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);

        switch (type) {
            case ACCOUNT_CONFIRMATION:
                Token token = tokenService.generate(user.getId());
                model.put("token", Base64.getEncoder().encode((token.getId() + ":" + tokenService.getTokenStr(token)).getBytes()));
                break;
            default:
                // todo
        }

        return model;
    }
}

package net.findeasily.website.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateModelException;
import net.findeasily.website.service.FreemarkerService;

@Configuration
public class FreemarkerConfig {
    private final freemarker.template.Configuration freemarkerConfig;
    private final FreemarkerService freemarkerService;

    @Value("${google.map.api.key}")
    private String googleMapApiKey;

    @Value("${google.recaptcha.site.key}")
    private String recaptchaSiteKey;


    public FreemarkerConfig(freemarker.template.Configuration freemarkerConfig, FreemarkerService freemarkerService) {
        this.freemarkerConfig = freemarkerConfig;
        this.freemarkerService = freemarkerService;
    }


    @PostConstruct
    public void setSharedVariables() throws TemplateModelException {
        freemarkerConfig.setSharedVariable("google_map_api_key", googleMapApiKey);
        freemarkerConfig.setSharedVariable("recaptcha_site_key", recaptchaSiteKey);
        freemarkerConfig.setSharedVariable("fmInstance", freemarkerService);
    }
}

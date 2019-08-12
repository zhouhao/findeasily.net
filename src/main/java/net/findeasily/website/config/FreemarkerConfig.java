package net.findeasily.website.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateModelException;

@Configuration
public class FreemarkerConfig {
    private final freemarker.template.Configuration freemarkerConfig;

    @Value("${google.map.api.key}")
    private String googleMapApiKey;

    @Value("${google.recaptcha.site.key}")
    private String recaptchaSiteKey;

    public FreemarkerConfig(freemarker.template.Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }


    @PostConstruct
    public void setSharedVariables() throws TemplateModelException {
        freemarkerConfig.setSharedVariable("google_map_api_key", googleMapApiKey);
        freemarkerConfig.setSharedVariable("recaptcha_site_key", recaptchaSiteKey);
    }
}

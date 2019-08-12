package net.findeasily.website.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.RecaptchaResponse;

@Service
@Slf4j
public class RecaptchaService {
    @Value("${google.recaptcha.secret.key}")
    String recaptchaSecret;

    private static final Map<String, String> RECAPTCHA_ERROR_CODE = ImmutableMap.of(
            "missing-input-secret", "The secret parameter is missing",
            "invalid-input-secret", "The secret parameter is invalid or malformed",
            "missing-input-response", "The response parameter is missing",
            "invalid-input-response", "The response parameter is invalid or malformed",
            "bad-request", "The request is invalid or malformed"
    );

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";

    private final RestTemplateBuilder restTemplateBuilder;

    public RecaptchaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Optional<String> verifyRecaptcha(String ip, String recaptchaResponse) {
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);
        log.debug("Request body for recaptcha: {}", body);
        ResponseEntity<RecaptchaResponse> recaptchaResponseEntity =
                restTemplateBuilder.build()
                        .postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL +
                                        "?secret={secret}&response={response}&remoteip={remoteip}",
                                body, RecaptchaResponse.class, body);

        log.debug("Response from recaptcha: {}", recaptchaResponseEntity);

        RecaptchaResponse responseBody = recaptchaResponseEntity.getBody();
        boolean recaptchaSuccess = responseBody != null && responseBody.isSuccess();
        if (!recaptchaSuccess) {
            List<String> errorCodes = responseBody.getErrorCodes();
            return Optional.of(errorCodes.stream()
                    .map(RECAPTCHA_ERROR_CODE::get)
                    .collect(Collectors.joining(", ")));
        } else {
            return Optional.empty();
        }
    }
}

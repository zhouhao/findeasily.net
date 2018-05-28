package net.findeasily.website.domain.exception;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

public class GenericError {
    private HttpStatus status;
    private List<String> errors;

    public GenericError() {
    }

    public GenericError(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public GenericError(HttpStatus status, String error) {
        this.status = status;
        this.errors = Collections.singletonList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setError(String error) {
        this.errors = Collections.singletonList(error);
    }
}

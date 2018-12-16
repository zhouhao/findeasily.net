package net.findeasily.website.domain.exception;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericError {
    private HttpStatus status;
    private List<String> errors;

    public GenericError(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public GenericError(HttpStatus status, String error) {
        this.status = status;
        this.errors = Collections.singletonList(error);
    }

}

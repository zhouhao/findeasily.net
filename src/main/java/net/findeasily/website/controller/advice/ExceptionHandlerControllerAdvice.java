package net.findeasily.website.controller.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.exception.GenericError;
import net.findeasily.website.exception.UserCreationException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        return e.getMessage();
    }


    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<GenericError> handleUserCreationException(UserCreationException e) {
        return ResponseEntity.badRequest().body(new GenericError(HttpStatus.BAD_REQUEST, e.getMessages()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericError> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

}

package net.findeasily.website.exception;

public class UnsupportedMediaTypeException extends RuntimeException {
    private String message;

    public UnsupportedMediaTypeException(String message) {
        super(message);
        this.message = message;
    }
}

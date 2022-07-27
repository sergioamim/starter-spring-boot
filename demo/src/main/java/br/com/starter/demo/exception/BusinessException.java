package br.com.starter.demo.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message, Object... args) {
        super(String.format(message, args));
    }

    public BusinessException(String message) {
        super(message);
    }
}

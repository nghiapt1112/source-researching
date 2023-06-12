package com.demo.Icommerce.infrastructure.exception;

public class BaseException extends RuntimeException {
    protected String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }
    public BaseException(Throwable e) {
        super(e);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}

package com.demo.Icommerce.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class DomainException extends BaseException {

    public DomainException(String message) {
        super(message);
        this.message = message;
    }
    public DomainException(Throwable e) {
        super(e);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}

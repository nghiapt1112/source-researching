package com.example.demo.infrastructure.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public class ResourceNotFoundException extends WebClientException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
}

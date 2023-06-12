package com.demo.Icommerce.infrastructure.controller;

import com.demo.Icommerce.infrastructure.exception.DomainException;
import com.demo.Icommerce.infrastructure.payload.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class BaseController {

    @Autowired
    private Environment environment;

    private String getStrVal(String k) {
        return environment.getProperty(k, k);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleValidateException(WebExchangeBindException e) {
        ErrorResponse errorResponse = ErrorResponse.builder().code("-997").build();
        StringBuilder msg = new StringBuilder();
        for (ObjectError err : e.getBindingResult().getAllErrors()) {
            msg.append("[")
                    .append(((FieldError)err).getField())
                    .append("] ")
                    .append(err.getDefaultMessage())
            ;
            errorResponse.updateMessage(msg.toString());
            msg.setLength(0);
        }
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleRootException(DomainException e) {
        e.printStackTrace();
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse
                        .builder()
                        .message(getStrVal(e.getMessage()))
                        .code("-996")
                        .key(e.getMessage())
                        .build());
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

}

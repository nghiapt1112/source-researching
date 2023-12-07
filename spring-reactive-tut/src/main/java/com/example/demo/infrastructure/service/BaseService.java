package com.example.demo.infrastructure.service;

import com.example.demo.infrastructure.exception.ClientRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public abstract class BaseService {

    public static final String RECENT_PURCHASES_BY_USER = "/api/purchases/by_user/{userName}";
    public static final String ALL_PEOPLE_WHO_PREVIOUSLY_PURCHASED = "/api/purchases/by_product/{productId}";
    public static final String PRODUCT_INFO = "/api/products/{productId}";
    public WebClient client3;

    @Value("${app.external.host}")
    private String host;

    @PostConstruct
    public void init() {
        client3 = WebClient
                .builder()
                .baseUrl(host)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * @param api:   api name
     * @param param: param as a single request param (not supported for array type)
     * @param typed: type of response object
     * @param <O>
     * @return
     */
    public <O> Mono<O> get(String api, Object param, Class<O> typed) {
        return client3
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(api)
                        .build(param))
                .exchange()
                .doOnError(throwable -> {
                    throw new ClientRequestException(String.format("An error has occur when trying to access to:\nAPI: %s\n", api), throwable);
                })
                .doOnNext(response -> {
                    HttpStatus httpStatus = response.statusCode();
                    if (httpStatus.is4xxClientError() || httpStatus.is5xxServerError()) {
                        throw new ClientRequestException(String.format("An error has occur when trying to access to:\nAPI: %s\nStatus: %s\nReason: %s", api, httpStatus.value(), httpStatus.getReasonPhrase()));
                    }
                })
                .flatMap(clientResponse -> clientResponse.bodyToMono(typed));
    }

}

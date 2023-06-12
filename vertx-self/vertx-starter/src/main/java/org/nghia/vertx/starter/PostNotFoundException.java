package org.nghia.vertx.starter;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(UUID id) {
        super("Post id: " + id + " was not found. ");
    }
}

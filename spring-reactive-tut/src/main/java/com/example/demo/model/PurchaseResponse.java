package com.example.demo.model;

import com.example.demo.infrastructure.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class PurchaseResponse {

    @JsonProperty(value = "purchases")
    private List<PurchaseData> purchases;

    public List<PurchaseData> getPurchases() {
        return purchases;
    }

    public static void throwIfEmpty(PurchaseResponse recentPurchaseResponse, String userName) {
        if (CollectionUtils.isEmpty(recentPurchaseResponse.getPurchases())) {
            throw new ResourceNotFoundException(String.format("User with username of '%s' was not found", userName));
        }
    }
}

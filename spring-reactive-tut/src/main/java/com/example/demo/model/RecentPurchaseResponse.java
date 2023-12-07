package com.example.demo.model;

import java.util.List;

public class RecentPurchaseResponse {
    private String id;
    private String face;
    private Integer price;
    private Integer size;
    private List<String> recent;

    public RecentPurchaseResponse(Product product, List<String> recent) {
        this.id = product.getId();
        this.face = product.getFace();
        this.price = product.getPrice();
        this.size = product.getSize();
        this.recent = recent;
    }

    public String getId() {
        return id;
    }

    public String getFace() {
        return face;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getSize() {
        return size;
    }

    public List<String> getRecent() {
        return recent;
    }

}

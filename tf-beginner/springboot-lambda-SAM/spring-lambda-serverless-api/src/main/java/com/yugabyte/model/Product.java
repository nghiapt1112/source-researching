package com.yugabyte.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long productId;
    
    private String productName;

    private String description;
    
    @Column(columnDefinition = "numeric(10,2)")
    private double price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
    	return price;
    }
    
    public void setPrice(double price) {
    	this.price = price;
    }
}
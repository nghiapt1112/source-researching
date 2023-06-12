package com.demo.Icommerce.domain.product.converter;

import com.demo.Icommerce.domain.product.entity.Product;
import com.demo.Icommerce.domain.product.payload.ProductResponse;

import java.util.function.Function;

public class ProductConverter {
    public static Function<Product, ProductResponse> toResponse() {
        return product -> {
            ProductResponse res = new ProductResponse();
            res.setProductId(product.getId());
            res.setCategory(product.getCategory().getName());
            res.setDescription(product.getDescription());
            res.setName(product.getName());
            res.setPrice(product.getPrice());
            return res;
        };

    }
}

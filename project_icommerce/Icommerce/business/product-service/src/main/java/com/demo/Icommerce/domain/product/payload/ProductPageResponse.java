package com.demo.Icommerce.domain.product.payload;

import com.demo.Icommerce.domain.product.converter.ProductConverter;
import com.demo.Icommerce.domain.product.entity.Product;
import com.demo.Icommerce.infrastructure.payload.BasePageResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductPageResponse extends BasePageResponse<Product, ProductResponse> {

    private ProductPageResponse() {
    }

    public ProductPageResponse(Integer page, Integer size, Long total) {
        super(page, size, total);
    }

    @Override
    public void parse(List<Product> products) {
        this.setDetails(products.stream()
                .map(el -> ProductConverter.toResponse().apply(el))
                .collect(Collectors.toList()));
    }

}

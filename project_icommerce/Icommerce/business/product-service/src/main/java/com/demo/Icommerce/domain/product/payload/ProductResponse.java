package com.demo.Icommerce.domain.product.payload;

import com.demo.Icommerce.infrastructure.payload.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse extends BaseResponse {
    private Long productId;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;
}

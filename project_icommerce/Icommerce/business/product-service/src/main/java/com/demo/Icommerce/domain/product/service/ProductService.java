package com.demo.Icommerce.domain.product.service;

import com.demo.Icommerce.domain.product.payload.ProductPageRequest;
import com.demo.Icommerce.domain.product.payload.ProductPageResponse;
import com.demo.Icommerce.domain.product.payload.ProductResponse;

public interface ProductService {
    ProductPageResponse find(ProductPageRequest pageRequest);

    ProductResponse findDetail(ProductPageRequest pageRequest);
}

package com.demo.Icommerce.domain.product.service.impl;

import com.demo.Icommerce.domain.product.converter.ProductConverter;
import com.demo.Icommerce.domain.product.entity.Product;
import com.demo.Icommerce.domain.product.payload.ProductPageRequest;
import com.demo.Icommerce.domain.product.payload.ProductPageResponse;
import com.demo.Icommerce.domain.product.payload.ProductResponse;
import com.demo.Icommerce.domain.product.repository.ProductRepository;
import com.demo.Icommerce.domain.product.service.ProductService;
import com.demo.Icommerce.infrastructure.exception.DomainException;
import com.demo.Icommerce.infrastructure.logger.IcommerceLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductPageResponse find(ProductPageRequest pageRequest) {
        IcommerceLogger.logger.info("[ProductServiceImpl] Finding Products with parames: "+ pageRequest);
        List<Product> res = productRepository.find(pageRequest);
        long count = productRepository.getTotalResult(pageRequest);
        ProductPageResponse productPageResponse = new ProductPageResponse(pageRequest.getPage(), pageRequest.getSize(), count);
        productPageResponse.parse(res);
        return productPageResponse;
    }

    @Override
    public ProductResponse findDetail(ProductPageRequest pageRequest) {
        List<Product> res = productRepository.find(pageRequest);
        if (CollectionUtils.isEmpty(res)) {
            throw new DomainException("product.not_found");
        }
        return res.stream().map(el -> ProductConverter.toResponse().apply(el)).findFirst().get();
    }
}

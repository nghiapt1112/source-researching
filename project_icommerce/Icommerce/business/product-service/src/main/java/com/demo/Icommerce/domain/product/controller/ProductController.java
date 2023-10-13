package com.demo.Icommerce.domain.product.controller;

import com.demo.Icommerce.domain.product.payload.ProductCreateDTO;
import com.demo.Icommerce.domain.product.payload.ProductPageRequest;
import com.demo.Icommerce.domain.product.payload.ProductPageResponse;
import com.demo.Icommerce.domain.product.payload.ProductResponse;
import com.demo.Icommerce.domain.product.service.ProductService;
import com.demo.Icommerce.infrastructure.logger.IcommerceLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/find")
    public ProductPageResponse findProducts(ProductPageRequest productPageRequest) {
        IcommerceLogger.logger.info("[ProductController] filter: " + productPageRequest);
        return productService.find(productPageRequest);
    }

    @GetMapping("/find/{id}")
    public ProductResponse findDetail(@PathVariable Long id) {
        IcommerceLogger.logger.info("[ProductController] find detail: " + id);
        ProductPageRequest pageRequest = new ProductPageRequest();
        pageRequest.setPage(0);
        pageRequest.setSize(1);
        pageRequest.setId(id);
        return productService.findDetail(pageRequest);
    }

    @PostMapping("/create")
    public void createProduct (@RequestBody ProductCreateDTO productCreateDTO) {
        this.productService.create(productCreateDTO);
    }
}

package com.demo.Icommerce.domain.cart.controller;

import com.demo.Icommerce.domain.cart.entity.Cart;
import com.demo.Icommerce.domain.cart.payload.CartPayload;
import com.demo.Icommerce.domain.cart.service.CartService;
import com.demo.Icommerce.infrastructure.logger.IcommerceLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping()
    public void addToCart(@RequestBody CartPayload cartPayload) {
        IcommerceLogger.logger.info("[CartController] addToCart: " + cartPayload.getProductIds());
        cartService.addToCard(cartPayload.getProductIds());
    }

    @GetMapping("/find")
    public List<Cart> findAll() {
        return cartService.findAll();
    }

    @GetMapping("/find/{id}")
    public Cart findDetail(@PathVariable Long id) {
        return cartService.findById(id);
    }

}

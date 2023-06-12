package com.demo.Icommerce.domain.cart.service;

import com.demo.Icommerce.domain.cart.entity.Cart;

import java.util.Collection;
import java.util.List;

public interface CartService {
    void addToCard(Collection<Long> productIds);

    List<Cart> findAll();

    Cart findById(Long cartId);
}

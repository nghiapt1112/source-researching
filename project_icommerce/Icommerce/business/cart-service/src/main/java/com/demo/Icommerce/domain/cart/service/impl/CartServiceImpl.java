package com.demo.Icommerce.domain.cart.service.impl;

import com.demo.Icommerce.domain.cart.entity.Cart;
import com.demo.Icommerce.domain.cart.repository.CartRepository;
import com.demo.Icommerce.domain.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    @Transactional
    public void addToCard(Collection<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return;
        }
        String strProductIds = productIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        cartRepository.save(new Cart(strProductIds));
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Long cartId) {
        if (Objects.isNull(cartId)) {
            throw new RuntimeException("Cart can not be found");
        }
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cant can't be found"));
    }
}

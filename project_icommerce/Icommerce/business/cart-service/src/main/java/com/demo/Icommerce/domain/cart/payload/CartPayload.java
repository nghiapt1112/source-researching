package com.demo.Icommerce.domain.cart.payload;

import com.demo.Icommerce.infrastructure.payload.BaseObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class CartPayload extends BaseObject {
    private Collection<Long> productIds;
}

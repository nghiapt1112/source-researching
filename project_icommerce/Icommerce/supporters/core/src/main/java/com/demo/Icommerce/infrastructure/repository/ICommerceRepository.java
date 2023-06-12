package com.demo.Icommerce.infrastructure.repository;

import com.demo.Icommerce.infrastructure.payload.BaseEntity;
import com.demo.Icommerce.infrastructure.payload.PageRequest;

import java.util.List;

public interface ICommerceRepository<E extends BaseEntity, P extends PageRequest> {
    List<E> find(P e);

    long getTotalResult(P e);
}

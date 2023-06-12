package com.demo.Icommerce.domain.product.repository.impl;

import com.demo.Icommerce.domain.product.entity.Product;
import com.demo.Icommerce.domain.product.payload.ProductPageRequest;
import com.demo.Icommerce.domain.product.repository.ProductRepositoryCustom;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom<Product, ProductPageRequest> {
    private final String selectQuery = "SELECT p FROM Product p ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> find(ProductPageRequest pageRequest) {
        pageRequest.parseQuery();
        Query jpaQuery = this.entityManager.createQuery(selectQuery + pageRequest.getQueryCondition());
        if (!CollectionUtils.isEmpty(pageRequest.getQueryParams())) {
            pageRequest.getQueryParams().forEach((k, v) -> jpaQuery.setParameter(k, v));
        }
        jpaQuery.setFirstResult(pageRequest.getFirstResult());
        jpaQuery.setMaxResults(pageRequest.getSize());
        return jpaQuery.getResultList();
    }

    //TODO: Using generic type to make this func be abstract.
    public long getTotalResult(ProductPageRequest pageRequest) {
        Query totalQuery = entityManager.createQuery("SELECT COUNT(p.id) FROM Product p " + pageRequest.getQueryCondition());
        if (!CollectionUtils.isEmpty(pageRequest.getQueryParams())) {
            pageRequest.getQueryParams().forEach((k, v) -> totalQuery.setParameter(k, v));
        }
        return (long) totalQuery.getSingleResult();
    }

}

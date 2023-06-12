package com.demo.Icommerce.domain.product.payload;

import com.demo.Icommerce.infrastructure.payload.PageRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
public class ProductPageRequest extends PageRequest {
    private String category;
    private String name;
    private String branch;
    private String color;
    private BigDecimal price;

    @Override
    public void parseQuery() {
        if (Objects.nonNull(this.queryCondition) && Objects.nonNull(this.queryParams)) {
            return;
        }
        StringBuilder condition = new StringBuilder(" WHERE p.deleted <> TRUE ");
        Map<String, Object> params = new HashMap<>();

        if (StringUtils.hasText(this.getCategory())) {
            condition.append(" AND p.category.name LIKE LOWER(:category)");
            params.put("category", "%" + this.getCategory() + "%");
        }

        if (StringUtils.hasText(this.getName())) {
            condition.append(" AND p.name LIKE LOWER(:name)");
            params.put("name", "%" + this.getName() + "%");
        }
        if (StringUtils.hasText(this.getBranch())) {
            condition.append(" AND p.branch LIKE LOWER(:branch)");
            params.put("branch", "%" + this.getBranch() + "%");
        }
        if (StringUtils.hasText(this.getColor())) {
            condition.append(" AND p.colour = :color");
            params.put("color", this.getColor());
        }
        if (Objects.nonNull(this.getPrice())) {
            condition.append(" AND p.price = :price");
            params.put("price", this.getPrice());
        }

        if (Objects.nonNull(this.getId())) {
            condition.append(" AND p.id = :id");
            params.put("id", this.getId());
        }
        this.queryCondition = condition;
        this.queryParams = params;
    }
}

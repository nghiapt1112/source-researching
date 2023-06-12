package com.demo.Icommerce.infrastructure.payload;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends BaseObject {
    @Column
    private Long createdAt;

    @Column
    private Long createdBy;

    @Column
    private Long updatedAt;

    @Column
    private Long updatedBy;

    @Column
    private Boolean deleted = false;
}

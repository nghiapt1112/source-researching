package com.example.filex.excel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseDTO {
    protected Long createdDate;
    protected Long updatedDate;
    protected Long createdBy;
    protected Long updatedBy;
}

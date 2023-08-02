package com.example.filex.excel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseHandler<T> {
    protected String strValue;
    protected ValidationRule validationRule;
    protected abstract T getVal();
    protected abstract void validate();

}

package com.example.filex.excel;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Getter
@Setter
public abstract class BaseHandler<T> {
    protected String strValue;
    protected ValidationRule validationRule;

    protected abstract T getVal();

    protected abstract void validate();


    protected T getInstance() throws Exception {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        return (T) ((Class) ((ParameterizedType) this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
    }
}

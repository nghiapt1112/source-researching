package com.example.filex.excel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface Handle<T> {
    T getVal();

    void validate(List<ExcelError> excelErrors);

    default T createNewInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (T) ((Class) ((ParameterizedType) this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[0]).getDeclaredConstructor().newInstance();
    }
}


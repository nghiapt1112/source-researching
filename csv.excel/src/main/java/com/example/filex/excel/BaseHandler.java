//package com.example.filex.excel;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.ParameterizedType;
//import java.util.List;
//
//@Getter
//@Setter
//public abstract class BaseHandler<T> {
//    protected String strValue;
//    protected ValidationRule validationRule;
//
//    protected abstract T getVal();
//
//    protected abstract void validate(List<ExcelError> excelErrors);
//
//
//    protected T createNewInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        return (T) ((Class) ((ParameterizedType) this.getClass().
//                getGenericSuperclass()).getActualTypeArguments()[0]).getDeclaredConstructor().newInstance();
//    }
//}

package com.example.filex.excel;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Supplier;

@Getter
public abstract class RowHandler<DTO extends BaseDTO> extends BaseHandler<DTO> {
    protected List<ColumnHandler> columnHandlers;
    protected Row row;
    protected DTO dto;
    protected Supplier<DTO> dtoSupplier;

    protected RowHandler withRow(Row row) {
        this.row = row;
        return this;
    }

    protected DTO parseToDTO() {
        this.getColumnHandlers().forEach(cellHandler -> {
            cellHandler.validate();
        });
        return dtoSupplier.get();
    }


    protected DTO getInstance() throws Exception {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        return (DTO) ((Class) ((ParameterizedType) this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();

    }

}

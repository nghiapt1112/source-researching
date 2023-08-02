package com.example.filex.excel;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

@Getter
public abstract class RowHandler<DTO extends BaseDTO> extends BaseHandler<DTO> {
    protected List<ColumnHandler> columnHandlers;
    protected Row row;
    protected DTO dto;

    @Override
    protected DTO getVal() {
        return this.dto;
    }

    protected RowHandler withRow(Row row) throws Exception {
        this.row = row;
        this.dto = this.getInstance();
        return this;
    }

    protected DTO parseToDTO() {
        this.getColumnHandlers().forEach(cellHandler -> {
            cellHandler.validate();
        });
        this.parseDataFromCells();
        return this.dto;
    }

    protected abstract RowHandler parseDataFromCells();


}

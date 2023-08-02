package com.example.filex.excel.user;

import com.example.filex.excel.ColumnHandler;
import com.example.filex.excel.ValidationRule;
import org.apache.poi.ss.usermodel.Cell;

public class EmailColHandler extends ColumnHandler<String> {

    public EmailColHandler(ValidationRule validationRule) {
        super(validationRule);
    }

    @Override
    protected ColumnHandler withCell(Cell cell) {
        this.cell = cell;
        return this;
    }

    @Override
    protected String getVal() {
        return this.cell.getRichStringCellValue().getString();
    }

    @Override
    protected void validateOtherXXX() {

    }

}

package com.example.filex.excel.user;

import com.example.filex.excel.ColumnHandler;
import com.example.filex.excel.ExcelError;
import com.example.filex.excel.ExcelErrorLevel;
import com.example.filex.excel.ValidationRule;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;
import java.util.Objects;

public class EmailColHandler extends ColumnHandler<String> {

    public EmailColHandler(ValidationRule validationRule) {
        super(validationRule);
    }

//    @Override
//    protected ColumnHandler withCell(Cell cell) {
//        this.cell = cell;
//        return this;
//    }

    @Override
    public String getVal() {
        return this.cell.getRichStringCellValue().getString();
    }

    /**
     * Hàm này để validate từng cell, theo validationRule trc đấy của nó
     */
    @Override
    protected void validateCustomFields(List<ExcelError> excelErrors) {
        String val = this.cell.getStringCellValue();

        if (Objects.nonNull(val)
                && Objects.equals("Nghia 10", val)
                || Objects.equals("Nghia 20", val)
                || Objects.equals("Nghia 30", val)) {
            excelErrors.add(new ExcelError(cell.getColumnIndex(), cell.getColumnIndex(), "import.user.email_minLength_failed", ExcelErrorLevel.ERROR));
        }
    }

}

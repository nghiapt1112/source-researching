package com.example.filex.excel.user;

import com.example.filex.excel.ColumnHandler;
import com.example.filex.excel.ExcelError;
import com.example.filex.excel.ExcelErrorLevel;
import com.example.filex.excel.ValidationRule;

import java.util.List;
import java.util.Objects;

public class PhoneColHandler extends ColumnHandler<String> {

    protected PhoneColHandler(ValidationRule validationRule) {
        super(validationRule);
    }


    @Override
    protected void validateCustomFields(List<ExcelError> excelErrors) {
        if (Objects.nonNull(this.cell.getStringCellValue()) &&
                (this.cell.getStringCellValue().equals("0359963584")
                        || this.cell.getStringCellValue().equals("0359963582"))
        ) {
            excelErrors.add(new ExcelError(cell.getColumnIndex(), cell.getColumnIndex(), "import.user.phone_invalidFormat", ExcelErrorLevel.ERROR));
        }
    }
}

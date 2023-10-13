package com.example.filex.excel;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class ColumnHandler<T> implements Handle<T> {
    protected Cell cell;
    protected ValidationRule validationRule;

    protected ColumnHandler(ValidationRule validationRule) {
        super();
        this.validationRule = validationRule;
    }

    @Override
    public T getVal() {
        return null;
    }

    public ColumnHandler withCell(Cell cell) {
        this.cell = cell;
        return this;
    }

    protected abstract void validateCustomFields(List<ExcelError> excelErrors);

    @Override
    public void validate(List<ExcelError> excelErrors) {
        // TODO:
        //  - validate common here
        //  - Nếu là String thì check cái này
        //  - Thực ra có thể tạo thêm các StringHandler, NumberHandler, DateTimeHandler, TimeHandler, .vv... để xử lý cái này.
        //  btw, tuỳ ng viết sau. :))
        if (this.getVal() instanceof String) {
            if (Objects.nonNull(this.validationRule.getMin())
                    && ((String) this.getVal()).length() < this.validationRule.getMax()) {

//                throw new RuntimeException(" =========== loi roi ==========");
            }
            // TODO: validate regex ??
        }
        if (this.getVal() instanceof Number) {
            // TODO: validate number
        }

        if (this.getVal() instanceof Date) {
            // TODO: validate Date
        }
        // TODO: more more more
        this.validateCustomFields(excelErrors);
    }

}

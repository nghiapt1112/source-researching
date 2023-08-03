package com.example.filex.excel;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class ColumnHandler<T> extends BaseHandler<T> {
    protected Cell cell;

    public ColumnHandler(ValidationRule validationRule, List<ExcelError> excelErrors) {
        super();
        this.validationRule = validationRule;
    }

    protected abstract ColumnHandler withCell(Cell cell);

    protected abstract void validateCustomFields();

    @Override
    protected void validate() {
        // TODO:
        //  - validate common here
        //  - Nếu là String thì check cái này
        //  - Thực ra có thể tạo thêm các TypedHandler để xử lý cái này, btw, tuỳ ng viết sau. :))
        if (this.getVal() instanceof String) {
            if (Objects.nonNull(this.validationRule.getMin())
                    && ((String) this.getVal()).length() < this.validationRule.getMax()) {
                throw new RuntimeException(" =========== loi roi ==========");
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
        this.validateCustomFields();
    }

}

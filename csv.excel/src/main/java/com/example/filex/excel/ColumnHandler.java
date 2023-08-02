package com.example.filex.excel;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Objects;

public abstract class ColumnHandler<T> extends BaseHandler<T> {
    protected Cell cell;
    public ColumnHandler(ValidationRule validationRule) {
        super();
        this.validationRule = validationRule;
    }

    protected abstract ColumnHandler withCell(Cell cell);
    protected abstract void validateOtherXXX();

    @Override
    protected void validate() {
        // TODO: validate common here
        if (this.getVal() instanceof String) {
            if (Objects.nonNull(this.validationRule.getMin())
                    && ((String) this.getVal()).length() < this.validationRule.getMax()){
                throw new RuntimeException(" loi roi ");
            }
        }
        // TODO: more more more
        this.validateOtherXXX();
    }

}

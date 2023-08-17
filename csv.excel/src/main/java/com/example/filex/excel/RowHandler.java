package com.example.filex.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Getter
@NoArgsConstructor
public abstract class RowHandler<DTO extends BaseDTO> extends BaseHandler<DTO> {
    protected List<ColumnHandler> columnHandlers;
    protected int rowNum;
    protected DTO dto;
    protected List<ExcelError> excelErrors;

    @Override
    protected DTO getVal() {
        return this.dto;
    }


    protected RowHandler withRow(Row row) {
        this.rowNum = row.getRowNum();
        return this;
    }

    /**
     * Trong mỗi row, sẽ bao gồm 2 loại validate:
     * - Validate các cell : CellHandler.Validate
     * - Validate trong 1 row, tuỳ từ row sẽ có ràng buộc giữa các fields, nên hàm này tạo ra để validate các fields trong 1 row.
     */
    @Override
    protected void validate(List<ExcelError> excelErrors) {
        System.out.println("Validating row ======" + rowNum);
        this.getColumnHandlers().forEach(cellHandler -> cellHandler.validate(excelErrors));
        this.validateWithinRow(excelErrors);
    }

    public DTO parseToDTO() {
        int errSizeBeforeValidationSteps = excelErrors.size();
        this.validate(excelErrors);
        if (excelErrors.size() > errSizeBeforeValidationSteps) { // Contain error if errorSize increase
            return null;
        }
        try {
            // create new Instance of DTO then mapping value and return it , when passed all validation steps.
            this.dto = this.createNewInstance();
            this.mappingCellDataToDTOFields();
            return this.dto;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Validate các fields trong 1 row,
     * VD:
     * - FirstName và LastName là 1 cặp không đc giống nhau.
     */
    protected abstract void validateWithinRow(List<ExcelError> excelErrors);

    /**
     * Sau khi validate xong các cell và cả row, func này để map giá trị từng cell vào từng fields của DTO object
     *
     * @return
     */
    protected abstract RowHandler mappingCellDataToDTOFields();


}

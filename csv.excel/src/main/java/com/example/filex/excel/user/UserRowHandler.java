package com.example.filex.excel.user;

import com.example.filex.excel.ExcelError;
import com.example.filex.excel.ExcelErrorLevel;
import com.example.filex.excel.RowHandler;
import com.example.filex.excel.ValidationRule;
import org.apache.poi.ss.usermodel.Row;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserRowHandler extends RowHandler<UserDTO> {
    private final EmailColHandler emailColHandler;
    private final EmailColHandler emailColHandler2;
    private final EmailColHandler emailColHandler3;
    private final EmailColHandler emailColHandler4;
    private final PhoneColHandler phoneColHandler;
    private final EmailColHandler emailColHandler6;

    // rule cho các col
    // excelErros
    public UserRowHandler(ValidationRule validationRule) {
        this.emailColHandler = new EmailColHandler(validationRule);
        this.emailColHandler2 = new EmailColHandler(validationRule);
        this.emailColHandler3 = new EmailColHandler(validationRule);
        this.emailColHandler4 = new EmailColHandler(validationRule);
        this.phoneColHandler = new PhoneColHandler(validationRule);
        this.emailColHandler6 = new EmailColHandler(validationRule);
        this.columnHandlers = Arrays.asList(this.emailColHandler, this.emailColHandler2, this.emailColHandler3, this.emailColHandler4, this.phoneColHandler, this.emailColHandler6);
    }

    /**
     * Validate trong 1 row.
     * Sau khi parse xong DTO, thì sẽ có data của DTO dó, ở đây minh validate các fields của DTO.
     */
    @Override
    protected void validateWithinRow(List<ExcelError> excelErrors) {
        if (Objects.equals(this.emailColHandler.getVal(), "Nghia 31")) {
            excelErrors.add(new ExcelError(getRowNum(), 0, "import.user.firstName_blank", ExcelErrorLevel.ERROR));
            excelErrors.add(new ExcelError(getRowNum(), 0, "import.user.Email_blank", ExcelErrorLevel.ERROR));
            excelErrors.add(new ExcelError(getRowNum(), 0, "import.user.phone_duplicate", ExcelErrorLevel.ERROR));
        }
        //TODO: check các logic thưkc tế
    }

    /**
     * Add row vào, và set từng cell sẽ lấy giá trị ở col nào
     *
     * @param row
     * @return
     */
    @Override
    public RowHandler withRow(Row row) {
        super.withRow(row);
        this.emailColHandler.withCell(row.getCell(0));
        this.emailColHandler2.withCell(row.getCell(1));
        this.emailColHandler3.withCell(row.getCell(2));
        this.phoneColHandler.withCell(row.getCell(3));
        this.emailColHandler4.withCell(row.getCell(4));
        this.emailColHandler6.withCell(row.getCell(5));
        return this;
    }

    @Override
    protected RowHandler mappingCellDataToDTOFields() {
        this.dto.setFirstName(this.emailColHandler.getVal());
        this.dto.setLastName(this.emailColHandler2.getVal());
        this.dto.setEmail(this.emailColHandler3.getVal());
        this.dto.setPhone(this.phoneColHandler.getVal());
        this.dto.setAddress(this.emailColHandler6.getVal());
        return this;
    }
}

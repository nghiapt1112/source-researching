package com.example.filex.excel.user;

import com.example.filex.excel.RowHandler;
import org.apache.poi.ss.usermodel.Row;

import java.util.Arrays;

public class UserRowHandler extends RowHandler<UserDTO> {
    private final EmailColHandler emailColHandler;
    private final EmailColHandler emailColHandler2;
    private final EmailColHandler emailColHandler3;
    private final EmailColHandler emailColHandler4;
    private final EmailColHandler emailColHandler5;
    private final EmailColHandler emailColHandler6;
    private final EmailColHandler emailColHandler7;

    public UserRowHandler(
            EmailColHandler emailColHandler,
            EmailColHandler emailColHandler2,
            EmailColHandler emailColHandler3,
            EmailColHandler emailColHandler4,
            EmailColHandler emailColHandler5,
            EmailColHandler emailColHandler6,
            EmailColHandler emailColHandler7
    ) {
        this.emailColHandler = emailColHandler;
        this.emailColHandler2 = emailColHandler2;
        this.emailColHandler3 = emailColHandler3;
        this.emailColHandler4 = emailColHandler4;
        this.emailColHandler5 = emailColHandler5;
        this.emailColHandler6 = emailColHandler6;
        this.emailColHandler7 = emailColHandler7;
        this.columnHandlers = Arrays.asList(this.emailColHandler, this.emailColHandler2, this.emailColHandler3, this.emailColHandler4, this.emailColHandler5, this.emailColHandler6, this.emailColHandler7);
    }


    @Override
    protected UserDTO getVal() {
        return null;
    }

    @Override
    protected void validate() {

    }


    @Override
    public RowHandler withRow(Row row) {
        super.withRow(row);
        this.emailColHandler.withCell(row.getCell(0));
        this.emailColHandler2.withCell(row.getCell(1));
        this.emailColHandler3.withCell(row.getCell(2));
        this.emailColHandler4.withCell(row.getCell(3));
        this.emailColHandler5.withCell(row.getCell(4));
        this.emailColHandler6.withCell(row.getCell(5));
        return this;
    }


}

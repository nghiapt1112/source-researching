package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;

import java.util.List;

public class CheckDuplicateDBAction extends ValidateAction {
    @Override
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        System.out.println("check duplicate DB action, Size =====" + records.size());
        return super.execute(records, excelErrors);
    }
}

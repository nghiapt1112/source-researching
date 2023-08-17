package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;

import java.util.List;

public class PrintValueAction extends ValidateAction {
    @Override
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        System.out.println(" Print Acttion day, Size =====" + records.size());
        records.forEach(System.out::println);
        return super.execute(records, excelErrors);
    }
}
package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;

import java.util.List;

public class ExportAction extends BaseAction {
    @Override
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        System.out.println(" Export Acttion day");
        return super.execute(records, excelErrors);

    }
}

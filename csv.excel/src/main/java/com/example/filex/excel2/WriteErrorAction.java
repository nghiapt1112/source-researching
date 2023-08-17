package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class WriteErrorAction extends BaseAction {

    @Override
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        excelErrors.forEach(System.out::println);
        return super.execute(records, excelErrors);
    }
}

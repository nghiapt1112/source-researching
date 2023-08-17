package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckDuplicateDTOAction extends ValidateAction {
    @Override
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        System.out.println("check duplicate action, Size =====" + records.size());
        Set<BaseDTO> toCheckDuplicate = new HashSet<>();
        records.forEach(toCheckDuplicate::add);

        if (records.size() != toCheckDuplicate.size()) {
            // Duplicate roi anh Binh oi.
        }
        return super.execute(records, excelErrors);
    }

}

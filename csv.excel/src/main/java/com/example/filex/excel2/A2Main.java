package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;
import com.example.filex.excel.ValidationRule;
import com.example.filex.excel.user.UserRowHandler;

import java.util.ArrayList;
import java.util.List;

public class A2Main {
    public static void main(String[] args) {
        List<ExcelError> excelErrors = new ArrayList<>();
        ValidationRule emailValidationRule = getValidationRule();
        UserRowHandler userRowHandler = new UserRowHandler(emailValidationRule, excelErrors);
        List<BaseDTO> dtos = new ArrayList<>();

        ParseDTOAction parseAction = new ParseDTOAction("/Users/ptnghia1/data/sources/nghia/source-researching/csv.excel/src/main/resources/userImport.xlsx", userRowHandler);
        parseAction.next(new CheckDuplicateDTOAction());
        parseAction.next(new CheckDuplicateDBAction());
        parseAction.next(new PrintValueAction());
//        parseAction.next(new ExportAction());
        parseAction.next(new WriteErrorAction());
        parseAction.execute(dtos,excelErrors );

    }

    /**
     * Mock để get validation Rule -> có thể get từ json.
     *
     * @return
     */
    private static ValidationRule getValidationRule() {
        ValidationRule emailValidationRule = new ValidationRule();
        emailValidationRule.setMin(1);
        return emailValidationRule;
    }

}

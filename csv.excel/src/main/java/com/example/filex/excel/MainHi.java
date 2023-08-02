package com.example.filex.excel;

import com.example.filex.excel.user.UserDTO;
import com.example.filex.excel.user.UserRowHandler;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class MainHi {
    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream(new File("/Users/ptnghia1/data/sources/nghia/source-researching/csv.excel/src/main/resources/userImport.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
        Sheet sheet1 = wb.getSheetAt(0);

        ValidationRule emailValidationRule = getValidationRule();
        UserRowHandler userRowHandler = new UserRowHandler(emailValidationRule);

        for (int i = 1; i < sheet1.getLastRowNum(); i++) {
            Row row = sheet1.getRow(i);
            UserDTO dto = (UserDTO) userRowHandler
                    .withRow(row)
                    .parseToDTO();
            System.out.println(dto);
        }
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
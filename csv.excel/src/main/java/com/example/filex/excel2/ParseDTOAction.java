package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;
import com.example.filex.excel.HeaderMapping;
import com.example.filex.excel.ValidationRule;
import com.example.filex.excel.user.UserRowHandler;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.StreamSupport;

@NoArgsConstructor
public class ParseDTOAction extends BaseAction {
    private String pathFile;


    public ParseDTOAction(String pathFile) {
        this.pathFile = pathFile;
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

    @Override
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        System.out.println("Parse DTO action");
        // Get các rules cho từng col ở DB / json/ config
        // Ví dụ vs 1 rule cho tất cả các col:
        ValidationRule emailValidationRule = getValidationRule();


        // Tạo 1 instance duy nhất của UserRowHandler cho 1 sheetExcel.
        UserRowHandler userRowHandler = new UserRowHandler(emailValidationRule);


        try (
                FileInputStream fileInputStream = new FileInputStream(pathFile);
//             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

//                SXSSFWorkbook
             XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet1 = wb.getSheetAt(0);
//            int bytesRead;
//            byte[] buffer = new byte[1024];

//            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
//                 Process or write the bytes read from the file
//            }
            records = StreamSupport.stream(sheet1.spliterator(), false) // list of Row
                    .filter(row -> !this.isEmptyRow(row)) // skip emptyRow
                    .map(row -> userRowHandler.withRow(row).parseToDTO(excelErrors)) // parse to DTO, List ExcelError khi sử dụng Stream thì sẽ threadSafe <- cần check cái này.
                    //.filter(Objects::nonNull)// skip null - incase validation failed
                    .toList();
            return next.execute(records, excelErrors);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * TODO: implement with ExcelUtil.isEmtpyRow();
     *
     * @param row
     * @return
     */
    public boolean isEmptyRow(Row row) {
        return false;
    }

    public HeaderMapping getHeaderMapping(Row headerRow) {
        // TODO:
        //  - validate Header
        HeaderMapping headerMapping = new HeaderMapping();
        headerMapping.setIndex(0);
        headerMapping.setColTitle("firstName");
        return headerMapping;
    }

}

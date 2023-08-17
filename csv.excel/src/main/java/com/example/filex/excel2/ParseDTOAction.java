package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;
import com.example.filex.excel.HeaderMapping;
import com.example.filex.excel.user.UserRowHandler;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoArgsConstructor
public class ParseDTOAction extends BaseAction {
    private UserRowHandler userRowHandler;
    private String pathFile;



    public ParseDTOAction(String pathFile, UserRowHandler userRowHandler) {
        this.userRowHandler = userRowHandler;
        this.pathFile = pathFile;
    }

    @Override
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        System.out.println("Parse DTO action");
        try (FileInputStream file = new FileInputStream(pathFile);
             XSSFWorkbook wb = new XSSFWorkbook(file)) {
            Sheet sheet1 = wb.getSheetAt(0);

            sheet1.forEach(row -> {
                // new UserRolwHandler*();
            });

            records = StreamSupport.stream(sheet1.spliterator(), false)
                    .filter(row -> !this.isEmptyRow(row)) // skip emptyRow
                    .map(row -> this.userRowHandler.withRow(row).parseToDTO()) // parse to DTO
                    //.filter(Objects::nonNull)// skip null - incase validation failed
                    .collect(Collectors.toList());
            return next.execute(records, excelErrors);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * TODO: implement with ExcelUtil.checkIsEmtpyRow();
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

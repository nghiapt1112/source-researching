package com.example.filex;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ReadLargeExcelUsingSXSSF {
    public static void main(String[] args) {
        String excelFilePath = "path/to/your/excel/file.xlsx";

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new SXSSFWorkbook(new XSSFWorkbook(fis), 100)) { // keep 100 rows in memory, the rest will be flushed to the disk
             
            Sheet sheet = workbook.getSheetAt(0); // get first sheet
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    
                    switch (currentCell.getCellType()) {
                        case STRING:
                            System.out.print(currentCell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(currentCell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(currentCell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            break;
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

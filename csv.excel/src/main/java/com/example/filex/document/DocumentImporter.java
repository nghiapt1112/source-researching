package com.example.filex.document;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentImporter {

    public void readFile(String pathFile) throws IOException {
        FileInputStream file = new FileInputStream(pathFile);
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println(); break;
                    case NUMERIC: System.out.println(); break;
                    case BOOLEAN: System.out.println(); break;
                    case FORMULA: System.out.println(); break;
                    default: data.get(new Integer(i)).add(" ");
                }
            }
            i++;
        }
    }
}

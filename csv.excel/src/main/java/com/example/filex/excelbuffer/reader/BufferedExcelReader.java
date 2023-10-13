//package com.example.filex.excelbuffer.reader;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.function.Function;
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;
//
//public class BufferedExcelReader {
//    public static void main(String[] args) {
//        try {
//            // Provide the path to your Excel file
//            FileInputStream fileInputStream = new FileInputStream(new File("your_excel_file.xlsx"));
//
//            // Use XSSFWorkbook for better performance with large files
//            Workbook workbook = new XSSFWorkbook(fileInputStream);
//
//            // Assuming you are working with the first sheet (index 0)
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // Create a buffered reader to read the rows in chunks
//            int batchSize = 100; // Adjust the batch size as needed
//            int rowCount = 0;
//            for (Row row : sheet) {
//                // Iterate through the cells in the row
//                for (Cell cell : row) {
//                    // Depending on the cell type, you can retrieve the data accordingly
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                        case NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "\t");
//                            break;
//                        case BLANK:
//                            System.out.print("BLANK\t");
//                            break;
//                        default:
//                            System.out.print("DEFAULT\t");
//                    }
//                }
//                System.out.println(); // Move to the next row
//                rowCount++;
//
//                // Check if it's time to flush and clear memory
//                if (rowCount % batchSize == 0) {
//                    ((XSSFWorkbook) workbook).getPackage().getSharedStringsTable().clear();
//                    System.gc(); // Explicitly call garbage collection to release memory
//                }
//            }
//
//            // Close the FileInputStream
//            fileInputStream.close();
//
//            // Close the workbook
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

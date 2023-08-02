package com.example.filex.excel;

import com.example.filex.excel.user.EmailColHandler;
import com.example.filex.excel.user.UserDTO;
import com.example.filex.excel.user.UserRowHandler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collections;

public class MainHi {
    public static void main(String[] args) {
        XSSFWorkbook wb = null;
        Sheet sheet1 = wb.getSheetAt(0);
        ValidationRule emailValidationRUle = new ValidationRule();

        EmailColHandler emailHandler = new EmailColHandler(emailValidationRUle);
        EmailColHandler emailHandler2 = new EmailColHandler(emailValidationRUle);
        EmailColHandler emailHandler3 = new EmailColHandler(emailValidationRUle);
        EmailColHandler emailHandler4 = new EmailColHandler(emailValidationRUle);
        EmailColHandler emailHandler5 = new EmailColHandler(emailValidationRUle);
        EmailColHandler emailHandler6 = new EmailColHandler(emailValidationRUle);
        EmailColHandler emailHandler7 = new EmailColHandler(emailValidationRUle);

        UserRowHandler userRowHandler = new UserRowHandler(emailHandler, emailHandler2, emailHandler3, emailHandler4, emailHandler5 , emailHandler6, emailHandler7);


        sheet1.forEach(row -> {
            UserDTO dto = (UserDTO) userRowHandler.withRow(row).parseToDTO();

        });
    }
}

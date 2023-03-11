package com.example.filex.document;

//import com.px.tool.domain.user.User;
//import com.px.tool.domain.user.service.UserService;
//import com.px.tool.infrastructure.model.payload.AbstractPayLoad;
//import com.px.tool.infrastructure.utils.CollectionUtils;
//import com.px.tool.infrastructure.utils.ExcelImageService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class DocumentExporter<O extends Object> {

//    protected ExcelImageService excelImageService;
//    protected UserService userService;

    private XSSFWorkbook workbook;
    private O payload;

//    public DocumentExporter(ExcelImageService excelImageService, UserService userService) {
//        this.excelImageService = excelImageService;
//        this.userService = userService;
//    }

    protected XSSFWorkbook getWorkbook() {
        return this.workbook;
    }

    protected XSSFSheet getSheet(){
        return this.getWorkbook().getSheetAt(0);
    }

    protected O getPayload() {
        return payload;
    }

    public void export(InputStream fis, OutputStream response, O payload) {
        try {
            this.payload = payload;
            this.workbook = new XSSFWorkbook(fis);

            writeFixedData();
            copyRows();
            writePhanChuKy();
            writeDynamicData();
            this.getWorkbook().write(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.flush();
        } catch (IOException e) {
        }

    }

    protected abstract void register();

    protected abstract int totalLines();

    protected abstract void writeFixedData();

    protected abstract void copyRows();

    protected abstract void writeDynamicData() throws IOException;

    protected abstract void writePhanChuKy() throws IOException;

    /**
     *
     *
     *
     */
    protected String getVal(Map<Long, String> map, Long key) {
        if (key == null) {
            return "";
        }
        if (map.containsKey(key)) {
            return map.get(key);
        } else return "";
    }

    protected void setCellVal(Row row, int col, String val) {
        row.getCell(col).setCellValue(val);
    }

    protected byte[] imageData(String base64) {
        if (Objects.isNull(base64)) {
            return "".getBytes();
        }
        return Base64.getDecoder().decode(base64.substring(base64.indexOf(",") + 1).getBytes(StandardCharsets.UTF_8));
    }

    protected String fillUserInfo(Long userId, Map<Long, User> userById) {
        if (Objects.isNull(userId) || !userById.containsKey(userId)) {
            return "";
        }
        return userById.get(userId).getAlias();
    }

    protected String getNoiNhan(Map<Long, User> userById, List<Long> cusReceivers) {
        if (CollectionUtils.isEmpty(cusReceivers)) {
            return "";
        }
        return cusReceivers.stream()
                .map(userId -> this.fillUserInfo(userId, userById))
                .filter(userName -> !StringUtils.isEmpty(userName))
                .collect(Collectors.joining(","));
    }

//    protected Map<Long, User> userById(){
//        return this.userService.userById();
//    }

}

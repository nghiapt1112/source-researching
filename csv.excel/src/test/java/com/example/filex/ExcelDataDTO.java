package com.example.filex;

public class ExcelDataDTO {
    private String column1;
    private String column2;
    // ... Other columns

    // Getters, setters, and other methods


    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    @Override
    public String toString() {
        return "ExcelDataDTO{" +
                "column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                '}';
    }
}
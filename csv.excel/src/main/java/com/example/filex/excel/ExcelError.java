package com.example.filex.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExcelError {
    private int colIndex;
    private int cellIndex;
    private String errorCode;
    private ExcelErrorLevel errorLevel; // INFO, WARN, ERROR

    public ExcelError(int colIndex, int cellIndex, String errorCode, ExcelErrorLevel errorLevel) {
        this.colIndex = colIndex;
        this.cellIndex = cellIndex;
        this.errorCode = errorCode;
        this.errorLevel = errorLevel;
    }
}

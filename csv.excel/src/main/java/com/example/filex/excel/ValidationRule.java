package com.example.filex.excel;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationRule {
    private int min;
    private int max;
    private boolean require;
    private String regex;
}

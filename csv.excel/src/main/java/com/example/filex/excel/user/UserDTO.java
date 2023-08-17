package com.example.filex.excel.user;

import com.example.filex.excel.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}

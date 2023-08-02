package com.example.filex.excel.user;

import com.example.filex.excel.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String email;

}

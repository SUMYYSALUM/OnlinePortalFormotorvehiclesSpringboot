package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class AdminRequestDTO {
    private String firstname;
    private String lastname;
    private String address;
    private String phonenumber;
    private String oldpassword;
    private String password;

    
}

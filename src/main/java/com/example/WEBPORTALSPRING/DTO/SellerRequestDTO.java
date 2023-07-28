package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class SellerRequestDTO {
    private String firstName;
    private String lastName;
    private String oldPassword;
    private String password;
    private String region;
    private String district;
    private String ward;
    private String phonenumber;

}

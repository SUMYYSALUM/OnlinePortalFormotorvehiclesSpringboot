package com.example.WEBPORTALSPRING.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SpareOrderResDTO {

    private int spareOrderId;

    private LocalDate orderDate;
    private int quantity;
    private long amount;

    private int partId;
    private String partPic;
    private String partName;
    private long price;
    private String description;
    private String motorModel;
    private String motorMake;

    private int customerId;
    private String firstname;
    private String lastname;
    private String region;
    private String district;
    private String ward;
    private String phonenumber;

    private int sellerId;
    private String Sfirstname;
    private String Slastname;
    private String Sregion;
    private String Sdistrict;
    private String Sward;
    private String Sphonenumber;

    
    
}

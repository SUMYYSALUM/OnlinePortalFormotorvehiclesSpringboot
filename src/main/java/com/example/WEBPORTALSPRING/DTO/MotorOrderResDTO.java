package com.example.WEBPORTALSPRING.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MotorOrderResDTO {

    
    private int motorOrderId;

    private LocalDate orderDate;
    private int quantity;
    private long amount;

    private String motorPic;
    private String motorName;
    private String identification;
    private String interiorColor;
    private String exteriorColor;
    private String fuelType;
    private String transmissionType;
    private String motorCondition;
    private long motorPrice;

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

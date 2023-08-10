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
    private String price;
    private String description;
    private String motorModel;
    private String motorMake;

    private int customerId;
    private String firstname;
    private String lastname;
    private String region;
    private String district;
    private String ward;
    
    
}

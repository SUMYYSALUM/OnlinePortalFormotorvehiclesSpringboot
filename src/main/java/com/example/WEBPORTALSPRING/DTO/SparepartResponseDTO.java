package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class SparepartResponseDTO {
    private int partId;
    private String partPic;
    private String partName;
    private String price;
    private String description;
    private String motorModel;
    private String motorMake;


    private int sellerId;
    private String firstName;
    private String lastName;
    private String region;
    private String district;
    private String ward;

}

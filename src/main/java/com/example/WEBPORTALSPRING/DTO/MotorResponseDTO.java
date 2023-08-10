package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class MotorResponseDTO {
    private int motorId;
    private String motorPic;
    private String motorName;
    private String identification;
    private String interiorColor;
    private String exteriorColor;
    private String fuelType;
    private String transmissionType;
    private String motorCondition;
    private String motorPrice;
    private String motorMileage;
    private String motorMade;
    private String motorModel;
    private String releaseYear;

    private int sellerId;
    private String firstName;
    private String lastName;
    private String region;
    private String district;
    private String ward;

}

package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class MotorRequestDTO {
    private int sellerId;
    private int motorId;
    private String motorPic;
    private String motorName;
    private String identification;
    private String interiorColor;
    private String exteriorColor;
    private String fuelType;
    private String transmissionType;
    private String motorCondition;
    private long motorPrice;
    private String motorMileage;
    private String motorMade;
    private String motorModel;
    private String releaseYear;

}

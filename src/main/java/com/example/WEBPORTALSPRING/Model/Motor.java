package com.example.WEBPORTALSPRING.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Motor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int motorId;
    private String motorPic;
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

    @ManyToOne
    private Seller seller;

}

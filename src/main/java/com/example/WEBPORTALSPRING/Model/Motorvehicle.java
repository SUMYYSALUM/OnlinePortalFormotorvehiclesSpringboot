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
public class Motorvehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int motorvehicleId;
 
   private String Identificationnumber;
    private String Interiorcolor;
    private String Extiriorcolor;
    private String Fueltype;
    private String Transmissiontype;
    private String motorCondition;
    private String motorPrice;
    private String motorMileage;
    private String motorMake;
    private String motorMade;
    private String releaseYear;

    @ManyToOne
    private Seller seller;

}

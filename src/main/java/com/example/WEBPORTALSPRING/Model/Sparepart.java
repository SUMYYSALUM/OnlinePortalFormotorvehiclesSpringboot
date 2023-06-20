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
public class Sparepart {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int sparepartId;
    private String Partname;
    private String Location;
    private String Price;
    private String Partdescription;
    private String Motorvehiclemodel;
    private String motorvehiclemake;

    @ManyToOne
    private Seller seller;
    
}

    


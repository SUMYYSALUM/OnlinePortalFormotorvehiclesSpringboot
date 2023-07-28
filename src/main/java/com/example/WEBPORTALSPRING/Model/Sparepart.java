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

    private int partId;
    private String partPic;
    private String partName;
    private String price;
    private String description;
    private String motorModel;
    private String motorMake;

    @ManyToOne
    private Seller seller;
    
}

    


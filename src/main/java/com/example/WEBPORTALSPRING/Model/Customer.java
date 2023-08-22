package com.example.WEBPORTALSPRING.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String profilePic;
    private String firstname;
    private String lastname;
    private String username;
    private String region;
    private String district;
    private String ward;
    private String phonenumber;
    private String password;
    private String email;

    
    
}

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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;
    private String profilePic;
    private String firstname;
    private String lastname;
    private String username;
    private String address;
    private String phonenumber;
    private String password;
    private String email;

    
   
    
}

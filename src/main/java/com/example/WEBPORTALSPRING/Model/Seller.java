package com.example.WEBPORTALSPRING.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table
public class Seller {
    @ Id
    @ GeneratedValue(strategy = GenerationType.AUTO)
    private int sellerId;
    private String profilePic;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String region;
    private String district;
    private String ward;
    private String phonenumber;

}

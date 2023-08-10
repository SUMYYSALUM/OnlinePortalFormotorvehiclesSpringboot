package com.example.WEBPORTALSPRING.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class MotorOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int motorOrderId;

    private LocalDate orderDate;
    private int quantity;
    private long amount;

    @OneToOne
    private Motor motor;


    @ManyToOne
    private Customer customer;

    
}

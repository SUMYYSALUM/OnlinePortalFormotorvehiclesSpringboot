package com.example.WEBPORTALSPRING.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.WEBPORTALSPRING.DTO.MotorOrderReqDTO;

import lombok.Data;

@Data
@Entity
@Table
public class SpareOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spareOrderId;

    private LocalDate orderDate;
    private int quantity;
    private long amount;

    @OneToOne
    private Sparepart sparepart;


    @ManyToOne
    private Customer customer;

}
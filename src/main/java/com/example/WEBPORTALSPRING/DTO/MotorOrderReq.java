package com.example.WEBPORTALSPRING.DTO;

import java.time.LocalDate;



import lombok.Data;

@Data
public class MotorOrderReq {

    private int motorOrderId;

    private LocalDate orderDate;
    private int quantity;
    private long amount;
    private int motorId;
    private int customerId;
    
}

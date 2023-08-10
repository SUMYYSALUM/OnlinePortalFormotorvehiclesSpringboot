package com.example.WEBPORTALSPRING.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SpareOrderReqDTO {
    private int spareOrderId;

    private LocalDate orderDate;
    private int quantity;
    private long amount;
    private int partId;
    private int customerId;

    
}

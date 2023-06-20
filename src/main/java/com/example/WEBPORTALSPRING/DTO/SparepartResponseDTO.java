package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class SparepartResponseDTO {
    private int sparepartId;
    private String Partname;
    private String Location;
    private String Price;
    private String Partdescription;
    private String Motorvehiclemodel;
    private String motorvehiclemake;

    private int sellerId;
    private String firstName;
    private String lastName;
    private String region;
    private String district;
    private String ward;

}

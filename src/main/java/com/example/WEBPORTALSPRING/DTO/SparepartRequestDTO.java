package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class SparepartRequestDTO {
        private int sellerId;
        private int sparepartId;
        private String Partname;
        private String Location;
        private String Price;
        private String Partdescription;
        private String Motorvehiclemodel;
        private String motorvehiclemake;
    
}

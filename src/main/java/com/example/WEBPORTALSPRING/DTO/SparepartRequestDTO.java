package com.example.WEBPORTALSPRING.DTO;

import lombok.Data;

@Data
public class SparepartRequestDTO {
        private int sellerId;
        private int partId;
        private String partPic;
        private String partName;
        private long price;
        private String description;
        private String motorModel;
        private String motorMake;

}

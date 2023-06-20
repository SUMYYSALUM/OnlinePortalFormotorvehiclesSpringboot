package com.example.WEBPORTALSPRING.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WEBPORTALSPRING.Model.Seller;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    
}

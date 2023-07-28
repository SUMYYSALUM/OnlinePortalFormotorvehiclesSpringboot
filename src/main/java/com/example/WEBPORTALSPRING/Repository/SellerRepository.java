package com.example.WEBPORTALSPRING.Repository;

import com.example.WEBPORTALSPRING.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WEBPORTALSPRING.Model.Seller;
import org.springframework.data.jpa.repository.Query;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    @Query(value = "select * from seller where email = ?1", nativeQuery = true)
    Seller getSellerByEmail(String email);

    
}

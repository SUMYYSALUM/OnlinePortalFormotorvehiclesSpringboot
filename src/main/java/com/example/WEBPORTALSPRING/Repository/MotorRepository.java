package com.example.WEBPORTALSPRING.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WEBPORTALSPRING.Model.Motor;

public interface MotorRepository extends JpaRepository<Motor,Integer> {
    
    @Query(value="select * from motor where seller_seller_id = ?1", nativeQuery=true)
    List<Motor> getBySellerId(int id); 

    
}

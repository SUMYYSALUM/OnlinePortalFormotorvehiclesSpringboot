package com.example.WEBPORTALSPRING.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WEBPORTALSPRING.Model.MotorOrder;
import com.example.WEBPORTALSPRING.Model.SpareOrder;

public interface MotorOrderRepository extends JpaRepository<MotorOrder, Integer> {
    
    
    @Query(value="select * from motor_order mo inner join motor m on mo.motor_motor_id = m.motor_id  where m.seller_seller_id = ?1", nativeQuery=true)
    List<MotorOrder> getBySellerId(int id); 

     @Query(value="select * from motor_order mo inner join customer c on mo.customer_customer_id = c.customer_id  where c.customer_id = ?1", nativeQuery=true)
    List<MotorOrder> getByCustomerId(int id); 
}

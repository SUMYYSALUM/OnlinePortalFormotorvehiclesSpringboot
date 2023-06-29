package com.example.WEBPORTALSPRING.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WEBPORTALSPRING.Model.Customer;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    @Query(value = "select * from customer where email = ?1", nativeQuery = true)
    Customer getCustomerByEmail(String email);
    
}

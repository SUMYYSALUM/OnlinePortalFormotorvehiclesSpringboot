package com.example.WEBPORTALSPRING.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WEBPORTALSPRING.Model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    
}

package com.example.WEBPORTALSPRING.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WEBPORTALSPRING.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
    
    @Query(value = "select * from admin where email = ?1", nativeQuery = true)
    Admin getAdminByEmail(String email);
    
}

    


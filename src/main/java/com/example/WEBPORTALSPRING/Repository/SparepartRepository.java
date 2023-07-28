package com.example.WEBPORTALSPRING.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WEBPORTALSPRING.Model.Sparepart;

public  interface SparepartRepository extends JpaRepository<Sparepart,Integer>{

    @Query(value="select * from sparepart where seller_seller_id = ?1", nativeQuery=true)
    List<Sparepart> getBySellerId(int id); 

}
 
    


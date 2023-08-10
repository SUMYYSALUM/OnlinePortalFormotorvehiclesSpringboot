package com.example.WEBPORTALSPRING.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WEBPORTALSPRING.Model.Motor;
import com.example.WEBPORTALSPRING.Model.SpareOrder;

public interface SpareOrderRepository extends JpaRepository<SpareOrder, Integer> {
 
    @Query(value="select * from spare_order so inner join sparepart sp on so.sparepart_part_id = sp.part_id  where sp.seller_seller_id = ?1", nativeQuery=true)
    List<SpareOrder> getBySellerId(int id); 

     @Query(value="select * from spare_order so inner join customer c on so.customer_customer_id = c.customer_id  where c.customer_id = ?1", nativeQuery=true)
    List<SpareOrder> getByCustomerId(int id); 
}

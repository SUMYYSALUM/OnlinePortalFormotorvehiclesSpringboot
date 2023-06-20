package com.example.WEBPORTALSPRING.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WEBPORTALSPRING.Exceptions.ResourceNotFoundException;
import com.example.WEBPORTALSPRING.Model.Seller;
import com.example.WEBPORTALSPRING.Repository.SellerRepository;
@CrossOrigin
@RestController
@RequestMapping("api/")
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/seller/{id}")
    public Optional<Seller> viewSellerById(@PathVariable int id){
        return sellerRepository.findById(id);
    }

    // api ya kuview table la Seller
    @GetMapping("/seller")
    public List<Seller> viewSellers(){
        return sellerRepository.findAll();
    }

    //api ya kujaza data za seller
    @PostMapping("/seller")
    public Seller addSeller(@RequestBody Seller seller){
        return sellerRepository.save(seller);
    }

     @PutMapping("/seller/{id}")
     public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody Seller seller) {
         Seller findSeller = sellerRepository.findById(id)
         .orElseThrow(()-> new ResourceNotFoundException("invalid Id"));

             findSeller.setFirstName(seller.getFirstName());
             findSeller.setLastName(seller.getLastName());
             findSeller.setUsername(seller.getUsername());
             findSeller.setEmail(seller.getEmail());
             findSeller.setPhonenumber(seller.getPhonenumber());
             findSeller.setPassword(seller.getPassword());
             findSeller.setRegion(seller.getRegion());
             findSeller.setDistrict(seller.getDistrict());
             findSeller.setWard(seller.getWard());

             
             Seller seller2= sellerRepository.save(findSeller);
             return ResponseEntity.ok(seller2);
         
     }

     @DeleteMapping("/seller/{id}")
     public ResponseEntity<Map<String, Boolean>> deleteSeller(@PathVariable int id){
        Seller seller = sellerRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
        sellerRepository.delete(seller);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Seller deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
     }





}

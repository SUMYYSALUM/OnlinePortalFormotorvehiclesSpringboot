package com.example.WEBPORTALSPRING.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.WEBPORTALSPRING.DTO.SellerRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private ModelMapper modelMapper;

     // api ya kuwahesabu sellers
    @GetMapping("/seller/count")
    public int countSellers(){
        return sellerRepository.findAll().size();
    }

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
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody SellerRequestDTO sellerDTO) {
        Seller findSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));

        // Check if the provided old password matches the current password
        if (!findSeller.getPassword().matches(sellerDTO.getOldPassword())) {
            throw new IllegalArgumentException("Incorrect old password");
        }

        // Perform data mapping using ModelMapper
        modelMapper.map(sellerDTO, findSeller);

        Seller updatedSeller = sellerRepository.save(findSeller);
        return ResponseEntity.ok(updatedSeller);
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


    @PostMapping("/seller/login")
    public ResponseEntity<?> sellerLogin(@RequestBody Seller seller){
        Seller seller1 = sellerRepository.getSellerByEmail(seller.getEmail());
        if(seller1.getPassword().equals(seller.getPassword())){
            return ResponseEntity.ok(seller1);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}

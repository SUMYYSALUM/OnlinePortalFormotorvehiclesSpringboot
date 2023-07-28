package com.example.WEBPORTALSPRING.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.example.WEBPORTALSPRING.DTO.SparepartRequestDTO;
import com.example.WEBPORTALSPRING.DTO.SparepartResponseDTO;
import com.example.WEBPORTALSPRING.Exceptions.ResourceNotFoundException;
import com.example.WEBPORTALSPRING.Model.Seller;
import com.example.WEBPORTALSPRING.Model.Sparepart;
import com.example.WEBPORTALSPRING.Repository.SparepartRepository;

import lombok.Data;

@Data
@CrossOrigin
@RestController
@RequestMapping("api/")

public class SparepartController {
    @Autowired
    private SparepartRepository sparepartRepository;

    @Autowired
    private ModelMapper modelMapper;

     // api ya kuwahesabu sparepart
    @GetMapping("/sparepart/count")
    public int countSpareparts(){
        return sparepartRepository.findAll().size();
    }

    @GetMapping("/sparepart/{id}")
    public Optional<Sparepart> viewSparepartById(@PathVariable int id){
        return sparepartRepository.findById(id);
    }

    // api ya kuview table la sparepart
    @GetMapping("/sparepart")
    public List<SparepartResponseDTO> viewSparepart(){
        List<SparepartResponseDTO> list = new ArrayList<>();
        for(Sparepart sparepart:sparepartRepository.findAll()){
            SparepartResponseDTO sparepartResponseDTO = modelMapper.map(sparepart, SparepartResponseDTO.class);

            sparepartResponseDTO.setFirstName(sparepart.getSeller().getFirstName());
            sparepartResponseDTO.setLastName(sparepart.getSeller().getLastName());
            sparepartResponseDTO.setDistrict(sparepart.getSeller().getDistrict());
            sparepartResponseDTO.setRegion(sparepart.getSeller().getRegion());
            sparepartResponseDTO.setWard(sparepart.getSeller().getWard());

            list.add(sparepartResponseDTO);
        }
        return list;
    }

    //api ya kujaza data za sparepart
    @PostMapping("/sparepart")
    public Sparepart addSpareparts(@RequestBody SparepartRequestDTO sparepartRequestDTO){
        
        Seller seller = new Seller();
        seller.setSellerId(sparepartRequestDTO.getSellerId());

        Sparepart sparepart= modelMapper.map(sparepartRequestDTO,Sparepart.class);

        sparepart.setSeller(seller);

        return sparepartRepository.save(sparepart);
    }

     @PutMapping("/sparepart/{id}")
     public ResponseEntity<?> updateSparepart(@PathVariable("id") int id, @RequestBody Sparepart sparepart) {
         Sparepart findSparepart = sparepartRepository.findById(id)
         .orElseThrow(()-> new ResourceNotFoundException("invalid Id"));

        
            findSparepart.setPartName(sparepart.getPartName());
            findSparepart.setPrice(sparepart.getPrice());
            findSparepart.setDescription(sparepart.getDescription());
            findSparepart.setMotorModel(sparepart.getMotorModel());
            findSparepart.setMotorMake(sparepart.getMotorMake());
    

            Sparepart sparepart2= sparepartRepository.save(findSparepart);
            return ResponseEntity.ok(sparepart2);
         
   
     }

     @DeleteMapping("/sparepart/{id}")
     public ResponseEntity<Map<String, Boolean>> deleteSpareparts(@PathVariable int id){
        Sparepart sparepart = sparepartRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
        sparepartRepository.delete(sparepart);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Sparepart seleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
     }

    @GetMapping("/sparepart/seller/{id}")
    public List<SparepartRequestDTO> getSparepartBySellerId(@PathVariable int id){
        List<SparepartRequestDTO> list = new ArrayList<>();
        for(Sparepart sparepart:sparepartRepository.getBySellerId(id)){
            SparepartRequestDTO sparepartRequestDTO = modelMapper.map(sparepart, SparepartRequestDTO.class);
            sparepartRequestDTO.setSellerId(sparepart.getSeller().getSellerId());
            list.add(sparepartRequestDTO);
        }
        return list;
    }

  
}

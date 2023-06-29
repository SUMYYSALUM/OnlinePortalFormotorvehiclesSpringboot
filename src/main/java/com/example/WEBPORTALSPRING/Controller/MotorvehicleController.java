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

import com.example.WEBPORTALSPRING.DTO.MotorVehicleRequestDTO;
import com.example.WEBPORTALSPRING.DTO.MotorVehicleResponseDTO;
import com.example.WEBPORTALSPRING.DTO.SparepartResponseDTO;
import com.example.WEBPORTALSPRING.Exceptions.ResourceNotFoundException;
import com.example.WEBPORTALSPRING.Model.Motorvehicle;
import com.example.WEBPORTALSPRING.Model.Seller;
import com.example.WEBPORTALSPRING.Model.Sparepart;
import com.example.WEBPORTALSPRING.Repository.MotorvehicleRepository;



@CrossOrigin
@RestController
@RequestMapping("api/")
public class MotorvehicleController {
    @Autowired
    private MotorvehicleRepository motorvehicleRepository;

     @Autowired
    private ModelMapper modelMapper;



    // api ya kuwahesabu motorvehicles
    @GetMapping("/motorvehicle/count")
    public int countMotorvehicles(){
        return motorvehicleRepository.findAll().size();
    }

    @GetMapping("/motorvehicle/{id}")
    public Optional<Motorvehicle> viewMotorVehicleById(@PathVariable int id){
        return motorvehicleRepository.findById(id);
    }

    // api ya kuview table la Motorvehicle
    @GetMapping("/motorvehicle")
    public List<MotorVehicleResponseDTO> viewMotorvehicle(){
         List<MotorVehicleResponseDTO> list = new ArrayList<>();
        for(Motorvehicle motorvehicle:motorvehicleRepository.findAll()){
            MotorVehicleResponseDTO motorvehicleResponseDTO = modelMapper.map(motorvehicle, MotorVehicleResponseDTO.class);
            motorvehicleResponseDTO.setFirstName(motorvehicle.getSeller().getFirstName());
            motorvehicleResponseDTO.setLastName(motorvehicle.getSeller().getLastName());
            motorvehicleResponseDTO.setDistrict(motorvehicle.getSeller().getDistrict());
            motorvehicleResponseDTO.setRegion(motorvehicle.getSeller().getRegion());
            motorvehicleResponseDTO.setWard(motorvehicle.getSeller().getWard());

             list.add(motorvehicleResponseDTO);
        }
        return list;
    }

    //api ya kujaza data za motorvehicle
    @PostMapping("/motorvehicle")
    public Motorvehicle addMotorvehicles(@RequestBody MotorVehicleRequestDTO motorvehicleRequestDTO){
        Seller seller = new Seller();
        seller.setSellerId(motorvehicleRequestDTO.getSellerId());

        Motorvehicle motorvehicle= modelMapper.map(motorvehicleRequestDTO,Motorvehicle.class);

        motorvehicle.setSeller(seller);

        return motorvehicleRepository.save(motorvehicle);
    }

     @PutMapping("/motorvehicle/{id}")
     public ResponseEntity<?> updateMotorvehicle(@PathVariable("id") int id, @RequestBody Motorvehicle motorvehicle) {
         Motorvehicle findMotorvehicle = motorvehicleRepository.findById(id)
         .orElseThrow(()-> new ResourceNotFoundException("invalid Id"));

        
            findMotorvehicle.setIdentificationnumber(motorvehicle.getIdentificationnumber());
            findMotorvehicle.setInteriorcolor(motorvehicle.getInteriorcolor());
            findMotorvehicle.setExtiriorcolor(motorvehicle.getExtiriorcolor());
            findMotorvehicle.setFueltype(motorvehicle.getFueltype());
            findMotorvehicle.setTransmissiontype(motorvehicle.getTransmissiontype());
            findMotorvehicle.setMotorCondition(motorvehicle.getMotorCondition());
            findMotorvehicle.setMotorPrice(motorvehicle.getMotorPrice());
            findMotorvehicle.setMotorMileage(motorvehicle.getMotorMileage());
            findMotorvehicle.setMotorMake(motorvehicle.getMotorMake());
            findMotorvehicle.setMotorMade(motorvehicle.getMotorMade());
            findMotorvehicle.setReleaseYear(motorvehicle.getReleaseYear());

            Motorvehicle motorvehicle2= motorvehicleRepository.save(findMotorvehicle);
            return ResponseEntity.ok(motorvehicle2);
         
   
     }
     @DeleteMapping("/motorvehicle/{id}")
     public ResponseEntity<Map<String, Boolean>> deleteMotorvehicles(@PathVariable int id){
        Motorvehicle motorvehicle = motorvehicleRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
        motorvehicleRepository.delete(motorvehicle);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Motorvehicle seleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
     }

    }

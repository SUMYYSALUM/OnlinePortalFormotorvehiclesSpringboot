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

import com.example.WEBPORTALSPRING.DTO.MotorRequestDTO;
import com.example.WEBPORTALSPRING.DTO.MotorResponseDTO;
import com.example.WEBPORTALSPRING.DTO.SparepartRequestDTO;
import com.example.WEBPORTALSPRING.Exceptions.ResourceNotFoundException;
import com.example.WEBPORTALSPRING.Model.Motor;
import com.example.WEBPORTALSPRING.Model.Seller;
import com.example.WEBPORTALSPRING.Model.Sparepart;
import com.example.WEBPORTALSPRING.Repository.MotorRepository;



@CrossOrigin
@RestController
@RequestMapping("api/")
public class MotorController {
    @Autowired
    private MotorRepository motorRepository;

     @Autowired
    private ModelMapper modelMapper;



    // api ya kuwahesabu motorvehicles
    @GetMapping("/motorvehicle/count")
    public int countMotorvehicles(){
        return motorRepository.findAll().size();
    }

    @GetMapping("/motorvehicle/{id}")
    public Optional<Motor> viewMotorVehicleById(@PathVariable int id){
        return motorRepository.findById(id);
    }

    // api ya kuview table la Motorvehicle
    @GetMapping("/motorvehicle")
    public List<MotorResponseDTO> viewMotorvehicle(){
         List<MotorResponseDTO> list = new ArrayList<>();
        for(Motor motor : motorRepository.findAll()){
            MotorResponseDTO motorvehicleResponseDTO = modelMapper.map(motor, MotorResponseDTO.class);
            motorvehicleResponseDTO.setFirstName(motor.getSeller().getFirstName());
            motorvehicleResponseDTO.setLastName(motor.getSeller().getLastName());
            motorvehicleResponseDTO.setDistrict(motor.getSeller().getDistrict());
            motorvehicleResponseDTO.setRegion(motor.getSeller().getRegion());
            motorvehicleResponseDTO.setWard(motor.getSeller().getWard());

             list.add(motorvehicleResponseDTO);
        }
        return list;
    }

    //api ya kujaza data za motorvehicle
    @PostMapping("/motorvehicle")
    public Motor addMotorvehicles(@RequestBody MotorRequestDTO motorvehicleRequestDTO){
        Seller seller = new Seller();
        seller.setSellerId(motorvehicleRequestDTO.getSellerId());

        Motor motor = modelMapper.map(motorvehicleRequestDTO, Motor.class);

        motor.setSeller(seller);

        return motorRepository.save(motor);
    }

     @PutMapping("/motorvehicle/{id}")
     public ResponseEntity<?> updateMotorvehicle(@PathVariable("id") int id, @RequestBody Motor motor) {
         Motor findMotor = motorRepository.findById(id)
         .orElseThrow(()-> new ResourceNotFoundException("invalid Id"));

        
            findMotor.setIdentification(motor.getIdentification());
            findMotor.setInteriorColor(motor.getInteriorColor());
            findMotor.setExteriorColor(motor.getExteriorColor());
            findMotor.setTransmissionType(motor.getTransmissionType());
            findMotor.setFuelType(motor.getFuelType());
            findMotor.setMotorModel(motor.getMotorModel());
            findMotor.setMotorCondition(motor.getMotorCondition());
            findMotor.setMotorPrice(motor.getMotorPrice());
            findMotor.setMotorMileage(motor.getMotorMileage());
            findMotor.setMotorMade(motor.getMotorMade());
            findMotor.setReleaseYear(motor.getReleaseYear());

            Motor motor2 = motorRepository.save(findMotor);
            return ResponseEntity.ok(motor2);
         
   
     }
     @DeleteMapping("/motorvehicle/{id}")
     public ResponseEntity<Map<String, Boolean>> deleteMotorvehicles(@PathVariable int id){
        Motor motor = motorRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
        motorRepository.delete(motor);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Motorvehicle seleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
     }

      @GetMapping("/motorvehicle/seller/{id}")
    public List<MotorRequestDTO> getMotorBySellerId(@PathVariable int id){
        List<MotorRequestDTO> list = new ArrayList<>();
        for(Motor motor:motorRepository.getBySellerId(id)){
            MotorRequestDTO motorRequestDTO = modelMapper.map(motor, MotorRequestDTO.class);
            motorRequestDTO.setSellerId(motor.getSeller().getSellerId());
            list.add(motorRequestDTO);
        }
        return list;
    }

    }

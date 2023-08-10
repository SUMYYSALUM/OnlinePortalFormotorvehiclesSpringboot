package com.example.WEBPORTALSPRING.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.WEBPORTALSPRING.DTO.MotorOrderReq;
import com.example.WEBPORTALSPRING.DTO.MotorOrderResDTO;
import com.example.WEBPORTALSPRING.DTO.MotorResponseDTO;
import com.example.WEBPORTALSPRING.DTO.SparepartRequestDTO;
import com.example.WEBPORTALSPRING.Model.Customer;
import com.example.WEBPORTALSPRING.Model.Motor;
import com.example.WEBPORTALSPRING.Model.MotorOrder;
import com.example.WEBPORTALSPRING.Model.SpareOrder;
import com.example.WEBPORTALSPRING.Model.Seller;
import com.example.WEBPORTALSPRING.Model.Sparepart;
import com.example.WEBPORTALSPRING.Repository.MotorRepository;
import com.example.WEBPORTALSPRING.Repository.MotorOrderRepository;
import com.example.WEBPORTALSPRING.Repository.SparepartRepository;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class MotorOrderController {

    @Autowired
    private MotorOrderRepository motorOrderRepository;

    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/motor-order")
    public MotorOrder makeMotorOrder(@RequestBody MotorOrderReq motorOrderReq) {

        Customer customer = new Customer();
        customer.setCustomerId(motorOrderReq.getCustomerId());

        Motor motor = new Motor();
        motor.setMotorId(motorOrderReq.getMotorId());

        MotorOrder motorOrder = modelMapper.map(motorOrderReq, MotorOrder.class);

        motorOrder.setCustomer(customer);
        motorOrder.setMotor(motor);

        return motorOrderRepository.save(motorOrder);
    }

    @GetMapping("/motor-order")
public List<MotorOrderResDTO> viewMotorOrders(){
    List<MotorOrderResDTO> list = new ArrayList<>();
    for(MotorOrder motorOrder : motorOrderRepository.findAll()){

        MotorOrderResDTO motorOrderResDTO = modelMapper.map(motorOrder, MotorOrderResDTO.class);
        
        motorOrderResDTO.setFirstname(motorOrder.getCustomer().getFirstname());
        motorOrderResDTO.setLastname(motorOrder.getCustomer().getLastname());
        motorOrderResDTO.setDistrict(motorOrder.getCustomer().getDistrict());
        motorOrderResDTO.setRegion(motorOrder.getCustomer().getRegion());
        motorOrderResDTO.setWard(motorOrder.getCustomer().getWard());

        motorOrderResDTO.setMotorPic(motorOrder.getMotor().getMotorPic());
        motorOrderResDTO.setMotorName(motorOrder.getMotor().getMotorName());
        motorOrderResDTO.setIdentification(motorOrder.getMotor().getIdentification());
        motorOrderResDTO.setInteriorColor(motorOrder.getMotor().getInteriorColor());
        motorOrderResDTO.setExteriorColor(motorOrder.getMotor().getExteriorColor());
        motorOrderResDTO.setFuelType(motorOrder.getMotor().getFuelType());
        motorOrderResDTO.setTransmissionType(motorOrder.getMotor().getTransmissionType());
        motorOrderResDTO.setMotorCondition(motorOrder.getMotor().getMotorCondition());
        motorOrderResDTO.setMotorPrice(motorOrder.getMotor().getMotorPrice());

        list.add(motorOrderResDTO);
    }
    return list;
}

@GetMapping("/motor-order/customer/{customerId}")
public List<MotorOrderResDTO> viewMotorOrdersByCustomerId(@PathVariable int customerId){
    List<MotorOrderResDTO> list = new ArrayList<>();
    List<MotorOrder> motorOrders = motorOrderRepository.getByCustomerId(customerId);
    
    for(MotorOrder motorOrder : motorOrders){
        MotorOrderResDTO motorOrderResDTO = modelMapper.map(motorOrder, MotorOrderResDTO.class);

        motorOrderResDTO.setFirstname(motorOrder.getCustomer().getFirstname());
        motorOrderResDTO.setLastname(motorOrder.getCustomer().getLastname());
        motorOrderResDTO.setDistrict(motorOrder.getCustomer().getDistrict());
        motorOrderResDTO.setRegion(motorOrder.getCustomer().getRegion());
        motorOrderResDTO.setWard(motorOrder.getCustomer().getWard());

        motorOrderResDTO.setMotorPic(motorOrder.getMotor().getMotorPic());
        motorOrderResDTO.setMotorName(motorOrder.getMotor().getMotorName());
        motorOrderResDTO.setIdentification(motorOrder.getMotor().getIdentification());
        motorOrderResDTO.setInteriorColor(motorOrder.getMotor().getInteriorColor());
        motorOrderResDTO.setExteriorColor(motorOrder.getMotor().getExteriorColor());
        motorOrderResDTO.setFuelType(motorOrder.getMotor().getFuelType());
        motorOrderResDTO.setTransmissionType(motorOrder.getMotor().getTransmissionType());
        motorOrderResDTO.setMotorCondition(motorOrder.getMotor().getMotorCondition());
        motorOrderResDTO.setMotorPrice(motorOrder.getMotor().getMotorPrice());

        list.add(motorOrderResDTO);
    }
    return list;
}

@GetMapping("/motor-order/seller/{sellerId}")
public List<MotorOrderResDTO> viewMotorOrdersBySellerId(@PathVariable int sellerId){
    List<MotorOrderResDTO> list = new ArrayList<>();
    List<MotorOrder> motorOrders = motorOrderRepository.getBySellerId(sellerId);
    
    for(MotorOrder motorOrder : motorOrders){
        MotorOrderResDTO motorOrderResDTO = modelMapper.map(motorOrder, MotorOrderResDTO.class);

        motorOrderResDTO.setFirstname(motorOrder.getCustomer().getFirstname());
        motorOrderResDTO.setLastname(motorOrder.getCustomer().getLastname());
        motorOrderResDTO.setDistrict(motorOrder.getCustomer().getDistrict());
        motorOrderResDTO.setRegion(motorOrder.getCustomer().getRegion());
        motorOrderResDTO.setWard(motorOrder.getCustomer().getWard());

        motorOrderResDTO.setMotorPic(motorOrder.getMotor().getMotorPic());
        motorOrderResDTO.setMotorName(motorOrder.getMotor().getMotorName());
        motorOrderResDTO.setIdentification(motorOrder.getMotor().getIdentification());
        motorOrderResDTO.setInteriorColor(motorOrder.getMotor().getInteriorColor());
        motorOrderResDTO.setExteriorColor(motorOrder.getMotor().getExteriorColor());
        motorOrderResDTO.setFuelType(motorOrder.getMotor().getFuelType());
        motorOrderResDTO.setTransmissionType(motorOrder.getMotor().getTransmissionType());
        motorOrderResDTO.setMotorCondition(motorOrder.getMotor().getMotorCondition());
        motorOrderResDTO.setMotorPrice(motorOrder.getMotor().getMotorPrice());

        list.add(motorOrderResDTO);
    }
    return list;
}


    @DeleteMapping("/motor-order/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMotorOrder(@PathVariable int id) {
        MotorOrder motorOrder = motorOrderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motor Order not found"));

        motorOrderRepository.delete(motorOrder);
    }





}

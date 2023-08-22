package com.example.WEBPORTALSPRING.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.WEBPORTALSPRING.DTO.SpareOrderReqDTO;
import com.example.WEBPORTALSPRING.DTO.SpareOrderResDTO;
import com.example.WEBPORTALSPRING.DTO.SpareOrderReqDTO;
import com.example.WEBPORTALSPRING.DTO.SpareOrderResDTO;
import com.example.WEBPORTALSPRING.Model.Customer;
import com.example.WEBPORTALSPRING.Model.Motor;
import com.example.WEBPORTALSPRING.Model.SpareOrder;
import com.example.WEBPORTALSPRING.Model.SpareOrder;
import com.example.WEBPORTALSPRING.Model.Sparepart;
import com.example.WEBPORTALSPRING.Repository.SpareOrderRepository;
import com.example.WEBPORTALSPRING.Repository.MotorRepository;
import com.example.WEBPORTALSPRING.Repository.SpareOrderRepository;
import com.example.WEBPORTALSPRING.Repository.SparepartRepository;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class SpareOrderController {

    @Autowired
    private SpareOrderRepository spareOrderRepository;

    @Autowired
    private SparepartRepository sparepartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/spare-order")
public SpareOrder makeSpareOrder(@RequestBody SpareOrderReqDTO spareOrderReqDTO) {
    Customer customer = new Customer();
    customer.setCustomerId(spareOrderReqDTO.getCustomerId());

    Sparepart sparepart = sparepartRepository.findById(spareOrderReqDTO.getPartId())
            .orElseThrow(() -> new IllegalArgumentException("Motor not found"));

    long price = sparepart.getPrice();
    long quantity = spareOrderReqDTO.getQuantity();

    long totalAmount = price * quantity;
    SpareOrder spareOrder = modelMapper.map(spareOrderReqDTO, SpareOrder.class);
    
    spareOrder.setCustomer(customer);
    spareOrder.setSparepart(sparepart);
    spareOrder.setAmount(totalAmount);
    return spareOrderRepository.save(spareOrder);
}


    @GetMapping("/spare-order")
    public List<SpareOrderResDTO> viewSpareOrders(){
         List<SpareOrderResDTO> list = new ArrayList<>();
        for(SpareOrder spareOrder : spareOrderRepository.findAll()){

            SpareOrderResDTO spareOrderResDTO = modelMapper.map(spareOrder, SpareOrderResDTO.class);
            
            spareOrderResDTO.setFirstname(spareOrder.getCustomer().getFirstname());
            spareOrderResDTO.setLastname(spareOrder.getCustomer().getLastname());
            spareOrderResDTO.setDistrict(spareOrder.getCustomer().getDistrict());
            spareOrderResDTO.setRegion(spareOrder.getCustomer().getRegion());
            spareOrderResDTO.setWard(spareOrder.getCustomer().getWard());
            spareOrderResDTO.setPhonenumber(spareOrder.getCustomer().getPhonenumber());
            
            spareOrderResDTO.setSfirstname(spareOrder.getSparepart().getSeller().getFirstName());
        spareOrderResDTO.setSlastname(spareOrder.getSparepart().getSeller().getLastName());
        spareOrderResDTO.setSdistrict(spareOrder.getSparepart().getSeller().getDistrict());
        spareOrderResDTO.setSregion(spareOrder.getSparepart().getSeller().getRegion());
        spareOrderResDTO.setSward(spareOrder.getSparepart().getSeller().getWard());
        spareOrderResDTO.setSphonenumber(spareOrder.getSparepart().getSeller().getPhonenumber());

            spareOrderResDTO.setPartId(spareOrder.getSparepart().getPartId());
            spareOrderResDTO.setPartPic(spareOrder.getSparepart().getPartPic());
            spareOrderResDTO.setPartName(spareOrder.getSparepart().getPartName());
            spareOrderResDTO.setPrice(spareOrder.getSparepart().getPrice());
            spareOrderResDTO.setDescription(spareOrder.getSparepart().getDescription());
            spareOrderResDTO.setMotorModel(spareOrder.getSparepart().getMotorModel());
            spareOrderResDTO.setMotorMake(spareOrder.getSparepart().getMotorMake());
            
            

             list.add(spareOrderResDTO);
        }
        return list;
    }


    @GetMapping("/spare-order/seller/{sellerId}")
public List<SpareOrderResDTO> viewSpareOrdersBySellerId(@PathVariable int sellerId) {
    List<SpareOrderResDTO> list = new ArrayList<>();
    List<SpareOrder> spareOrders = spareOrderRepository.getBySellerId(sellerId);

    for (SpareOrder spareOrder : spareOrders) {
        SpareOrderResDTO spareOrderResDTO = modelMapper.map(spareOrder, SpareOrderResDTO.class);

        spareOrderResDTO.setFirstname(spareOrder.getCustomer().getFirstname());
        spareOrderResDTO.setLastname(spareOrder.getCustomer().getLastname());
        spareOrderResDTO.setDistrict(spareOrder.getCustomer().getDistrict());
        spareOrderResDTO.setRegion(spareOrder.getCustomer().getRegion());
        spareOrderResDTO.setWard(spareOrder.getCustomer().getWard());
        spareOrderResDTO.setPhonenumber(spareOrder.getCustomer().getPhonenumber());

        spareOrderResDTO.setSfirstname(spareOrder.getSparepart().getSeller().getFirstName());
        spareOrderResDTO.setSlastname(spareOrder.getSparepart().getSeller().getLastName());
        spareOrderResDTO.setSdistrict(spareOrder.getSparepart().getSeller().getDistrict());
        spareOrderResDTO.setSregion(spareOrder.getSparepart().getSeller().getRegion());
        spareOrderResDTO.setSward(spareOrder.getSparepart().getSeller().getWard());
        spareOrderResDTO.setSphonenumber(spareOrder.getSparepart().getSeller().getPhonenumber());


        spareOrderResDTO.setPartId(spareOrder.getSparepart().getPartId());
        spareOrderResDTO.setPartPic(spareOrder.getSparepart().getPartPic());
        spareOrderResDTO.setPartName(spareOrder.getSparepart().getPartName());
        spareOrderResDTO.setPrice(spareOrder.getSparepart().getPrice());
        spareOrderResDTO.setDescription(spareOrder.getSparepart().getDescription());
        spareOrderResDTO.setMotorModel(spareOrder.getSparepart().getMotorModel());
        spareOrderResDTO.setMotorMake(spareOrder.getSparepart().getMotorMake());

        list.add(spareOrderResDTO);
    }
    return list;
}

@GetMapping("/spare-order/customer/{customerId}")
public List<SpareOrderResDTO> viewSpareOrdersByCustomerId(@PathVariable int customerId) {
    List<SpareOrderResDTO> list = new ArrayList<>();
    List<SpareOrder> spareOrders = spareOrderRepository.getByCustomerId(customerId);

    for (SpareOrder spareOrder : spareOrders) {
        SpareOrderResDTO spareOrderResDTO = modelMapper.map(spareOrder, SpareOrderResDTO.class);

        spareOrderResDTO.setFirstname(spareOrder.getCustomer().getFirstname());
        spareOrderResDTO.setLastname(spareOrder.getCustomer().getLastname());
        spareOrderResDTO.setDistrict(spareOrder.getCustomer().getDistrict());
        spareOrderResDTO.setRegion(spareOrder.getCustomer().getRegion());
        spareOrderResDTO.setWard(spareOrder.getCustomer().getWard());
        spareOrderResDTO.setPhonenumber(spareOrder.getCustomer().getPhonenumber());

        spareOrderResDTO.setSfirstname(spareOrder.getSparepart().getSeller().getFirstName());
        spareOrderResDTO.setSlastname(spareOrder.getSparepart().getSeller().getLastName());
        spareOrderResDTO.setSdistrict(spareOrder.getSparepart().getSeller().getDistrict());
        spareOrderResDTO.setSregion(spareOrder.getSparepart().getSeller().getRegion());
        spareOrderResDTO.setSward(spareOrder.getSparepart().getSeller().getWard());
        spareOrderResDTO.setSphonenumber(spareOrder.getSparepart().getSeller().getPhonenumber());


        spareOrderResDTO.setPartId(spareOrder.getSparepart().getPartId());
        spareOrderResDTO.setPartPic(spareOrder.getSparepart().getPartPic());
        spareOrderResDTO.setPartName(spareOrder.getSparepart().getPartName());
        spareOrderResDTO.setPrice(spareOrder.getSparepart().getPrice());
        spareOrderResDTO.setDescription(spareOrder.getSparepart().getDescription());
        spareOrderResDTO.setMotorModel(spareOrder.getSparepart().getMotorModel());
        spareOrderResDTO.setMotorMake(spareOrder.getSparepart().getMotorMake());

        list.add(spareOrderResDTO);
    }
    return list;
}

@GetMapping("/spare-order/count")
public int countAllOrders(){
    int count = spareOrderRepository.findAll().size();

    return count;
}

@GetMapping("/spare-order/seller/count/{sellerId}")
public int countSellerOrders(@PathVariable int sellerId){
    int count = spareOrderRepository.getBySellerId(sellerId).size();

    return count;
}


@GetMapping("/spare-order/customer/count/{customerId}")
public int countCustomerOrders(@PathVariable int customerId){
    int count = spareOrderRepository.getByCustomerId(customerId).size();

    return count;
}


   @DeleteMapping("/spare-order/{id}")
    public ResponseEntity<String> deleteSpareOrder(@PathVariable int id) {
        SpareOrder spareOrder = spareOrderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Spare Order not found"));

        spareOrderRepository.delete(spareOrder);

        return ResponseEntity.ok("Spare Order with ID " + id + " has been successfully deleted.");
    }


}

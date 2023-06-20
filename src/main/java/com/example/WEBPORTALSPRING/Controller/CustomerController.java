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
import com.example.WEBPORTALSPRING.Model.Customer;
import com.example.WEBPORTALSPRING.Repository.CustomerRepository;



@CrossOrigin
@RestController
@RequestMapping("api/")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/customer/{id}")
    public Optional<Customer> viewCustomerById(@PathVariable int id){
        return customerRepository.findById(id);
    }

    // api ya kuview table la customer
    @GetMapping("/customer")
    public List<Customer> viewCustomrs(){
        return customerRepository.findAll();
    }

    //api ya kujaza data za seller
    @PostMapping("/customer")
    public Customer addCustomers(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

     @PutMapping("/customer/{id}")
     public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody Customer customer) {
         Customer findCustomer = customerRepository.findById(id)
         .orElseThrow(()-> new ResourceNotFoundException("invalid Id"));

        
            findCustomer.setFirstname(customer.getFirstname());
            findCustomer.setLastname(customer.getLastname());
            findCustomer.setUsername(customer.getUsername());
            findCustomer.setEmail(customer.getEmail());
            findCustomer.setPhonenumber(customer.getPhonenumber());
            findCustomer.setPassword(customer.getPassword());
            findCustomer.setRegion(customer.getRegion());
            findCustomer.setDistrict(customer.getDistrict());
            findCustomer.setWard(customer.getWard());

            Customer customer2= customerRepository.save(findCustomer);
            return ResponseEntity.ok(customer2);
         
     }

     @DeleteMapping("/customer/{id}")
     public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable int id){
        Customer customer = customerRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
        customerRepository.delete(customer);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Customer seleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
     }





}

    


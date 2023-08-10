package com.example.WEBPORTALSPRING.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WEBPORTALSPRING.DTO.AdminRequestDTO;
import com.example.WEBPORTALSPRING.DTO.SellerRequestDTO;
import com.example.WEBPORTALSPRING.Exceptions.ResourceNotFoundException;
import com.example.WEBPORTALSPRING.Model.Admin;
import com.example.WEBPORTALSPRING.Model.Customer;
import com.example.WEBPORTALSPRING.Model.Seller;
import com.example.WEBPORTALSPRING.Repository.AdminRepository;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/admin/{id}")
    public Optional<Admin> viewAdminById(@PathVariable int id){
        return adminRepository.findById(id);
    }

    // api ya kuview table la admin
    @GetMapping("/admin")
    public List<Admin> viewAdmin(){
        return adminRepository.findAll();
    }
 //api ya kujaza data za admin
 @PostMapping("/admin")
 public Admin addAdmin(@RequestBody Admin admin){
     return adminRepository.save(admin);
 }
 
     @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") int id, @RequestBody AdminRequestDTO adminDTO) {
        Admin findAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));

        // Check if the provided old password matches the current password
        if (!findAdmin.getPassword().matches(adminDTO.getOldpassword())) {
            throw new IllegalArgumentException("Incorrect old password");
        }

        // Perform data mapping using ModelMapper
        modelMapper.map(adminDTO, findAdmin);

        Admin updatedAdmin = adminRepository.save(findAdmin);
        return ResponseEntity.ok(updatedAdmin);
    }

     @DeleteMapping("/admin/{id}")
     public ResponseEntity<Map<String, Boolean>> deleteAdmin(@PathVariable int id){
        Admin admin = adminRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Invalid id"));
        adminRepository.delete(admin);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Admin seleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
     }

     @PostMapping("/admin/login")
     public ResponseEntity<?> adminLogin(@RequestBody Admin admin){
        Admin admin1 = adminRepository.getAdminByEmail(admin.getEmail());
        if(admin1.getPassword().equals(admin.getPassword())){
            return ResponseEntity.ok(admin1);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
     

}

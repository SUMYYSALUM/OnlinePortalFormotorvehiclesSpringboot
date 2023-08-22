package com.example.WEBPORTALSPRING.Controller;

import java.util.Base64;
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
    // password encryption
    String plainPassword = admin.getPassword();
    String encryptedPassword = Base64.getEncoder().encodeToString(plainPassword.getBytes());
    admin.setPassword(encryptedPassword); 
    return adminRepository.save(admin);
 }
 
     @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") int id, @RequestBody AdminRequestDTO adminDTO) {
        Admin findAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        
        String plainPassword1 = adminDTO.getOldpassword();
        String encryptedPassword1 = Base64.getEncoder().encodeToString(plainPassword1.getBytes());

        // Check if the provided old password matches the current password
        if (!findAdmin.getPassword().matches(encryptedPassword1)) {
            throw new IllegalArgumentException("Incorrect old password");
        }

        // Perform data mapping using ModelMapper
        modelMapper.map(adminDTO, findAdmin);

        String plainPassword2 = findAdmin.getPassword();
        String encryptedPassword2 = Base64.getEncoder().encodeToString(plainPassword2.getBytes());

        findAdmin.setPassword(encryptedPassword2);
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

        // encrypting plain login password
        String plainPassword = admin.getPassword();
        String encryptedPassword = Base64.getEncoder().encodeToString(plainPassword.getBytes());
        
        // checking encrypted login password Comparing with encrypted existing password
        if(admin1.getPassword().equals(encryptedPassword)){
            return ResponseEntity.ok(admin1);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
     

}

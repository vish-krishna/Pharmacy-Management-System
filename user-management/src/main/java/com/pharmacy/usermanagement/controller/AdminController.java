package com.pharmacy.usermanagement.controller;

import com.pharmacy.usermanagement.entity.Admin;
import com.pharmacy.usermanagement.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/admin")
    public ResponseEntity<List<Admin>> getUsers(){
        try {
            return ResponseEntity.ok(adminService.getAdmin());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/admin")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.saveAdmin(admin));
    }

    @PutMapping("/admin/{adminId}")
    public ResponseEntity<Admin> updateUser(@RequestBody Admin admin,@PathVariable("adminId") String id){
        return ResponseEntity.ok(adminService.updateAdmin(admin,id));
    }

    @DeleteMapping("/admin/{adminId}")
    public  ResponseEntity<String> deleteAdmin(@PathVariable("adminId") String id){
        return ResponseEntity.ok(adminService.deleteAdmin(id));
    }
}

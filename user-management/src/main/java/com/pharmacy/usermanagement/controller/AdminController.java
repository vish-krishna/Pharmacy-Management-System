package com.pharmacy.usermanagement.controller;

import com.pharmacy.usermanagement.services.impl.EmailSenderService;
import com.pharmacy.usermanagement.models.Admin;
import com.pharmacy.usermanagement.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getUserById(@PathVariable("adminId") String adminId){
        return ResponseEntity.ok(adminService.getAdminById(adminId));
    }


    @GetMapping("")
    public ResponseEntity<List<Admin>> getUsers(){
        try {
            return ResponseEntity.ok(adminService.getAdmin());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping()
    public ResponseEntity createAdmin(@RequestBody Admin admin){
        try {
        ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.saveAdmin(admin));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body( emailSenderService.sendSimpleEmail(admin.getAdmin_email(),
                        "PHARMACARE: New Account Created ",
                        "Hey " + admin.getAdmin_name()+"      " +
                                "You have created an account on Pharmacare as admin."));
    }catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Admin not created. Check the inputs.");
    }
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateUser(@RequestBody Admin admin,@PathVariable("adminId") String id){
        return ResponseEntity.ok(adminService.updateAdmin(admin,id));
    }

    @DeleteMapping("/{adminId}")
    public  ResponseEntity<String> deleteAdmin(@PathVariable("adminId") String id){
        return ResponseEntity.ok(adminService.deleteAdmin(id));
    }
}

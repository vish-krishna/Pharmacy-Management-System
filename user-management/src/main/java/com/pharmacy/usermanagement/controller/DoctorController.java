package com.pharmacy.usermanagement.controller;

import com.pharmacy.usermanagement.entity.Doctor;
import com.pharmacy.usermanagement.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @GetMapping("/doctor")
    public ResponseEntity<List<Doctor>> getUsers(){
        try {
            return ResponseEntity.ok(doctorService.getDoctor());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }

    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> updateUser(@RequestBody Doctor doctor,@PathVariable("doctorId") String id){
        return ResponseEntity.ok(doctorService.updateDoctor(doctor,id));
    }

    @DeleteMapping("/doctor/{doctorId}")
    public  ResponseEntity<String> deleteDoctor(@PathVariable("doctorId") String id){
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }
}

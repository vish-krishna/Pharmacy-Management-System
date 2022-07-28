package com.pharmacy.usermanagement.controller;

import com.pharmacy.usermanagement.models.Doctor;
import com.pharmacy.usermanagement.services.DoctorService;
import com.pharmacy.usermanagement.services.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private EmailSenderService emailSenderService;




    @GetMapping()
    public ResponseEntity<List<Doctor>> getUsers(){
        try {
            return ResponseEntity.ok(doctorService.getDoctor());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @PostMapping()
//    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
//        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
//    }

    @PostMapping()
    public ResponseEntity createAdmin(@RequestBody Doctor doctor) {
        try {
            ResponseEntity.status(HttpStatus.CREATED)
                    .body(doctorService.saveDoctor(doctor));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body( emailSenderService.sendSimpleEmail(doctor.getDoctor_email(),
                            "PHARMACARE: New Account Created ",
                            "Hey " + doctor.getDoctor_name()+"      " +
                                    "You have created an account on Pharmacare as doctor."));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Doctor not created. Check the inputs.");
        }
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateUser(@RequestBody Doctor doctor,@PathVariable("doctorId") String id){
        return ResponseEntity.ok(doctorService.updateDoctor(doctor,id));
    }

    @DeleteMapping("/{doctorId}")
    public  ResponseEntity<String> deleteDoctor(@PathVariable("doctorId") String id){
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }
}

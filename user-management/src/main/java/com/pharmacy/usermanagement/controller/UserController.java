package com.pharmacy.usermanagement.controller;

import com.pharmacy.usermanagement.models.Users;
import com.pharmacy.usermanagement.services.UserService;
import com.pharmacy.usermanagement.services.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;


    @GetMapping("/user")
    public ResponseEntity<List<Users>> getUsers(){
        try {
            return ResponseEntity.ok(userService.getUsers());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping("/createUser")
    public ResponseEntity createAdmin(@RequestBody Users users) {
        try {
            ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.saveUser(users));

            return ResponseEntity.status(HttpStatus.CREATED).body(users);
//                    .body( emailSenderService.sendSimpleEmail(users.getUserEmail(),
//                            "PHARMACARE: New Account Created ",
//                            "Hey " + users.getUserName() +"      " +
//                                    "You have created an account on Pharmacare as "+ users.getUserRole() +"."));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User not created. Check the inputs.");
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user,@PathVariable("userId") String id){
        return ResponseEntity.ok(userService.updateUser(user,id));
    }

    @DeleteMapping("/user/{userId}")
    public  ResponseEntity<String> deleteUser(@PathVariable("userId") String id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}

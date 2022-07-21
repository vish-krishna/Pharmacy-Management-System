package com.pharmacy.usermanagement.controller;

import com.pharmacy.usermanagement.entity.Users;
import com.pharmacy.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public ResponseEntity<List<Users>> getUsers(){
        try {
            return ResponseEntity.ok(userService.getUsers());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/user")
    public ResponseEntity<Users> createUsers(@RequestBody Users user){
        return ResponseEntity.ok(userService.saveUser(user));
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

package com.pharmacy.usermanagement.resources;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    @RequestMapping("/{userId}")
    public String getUser(@PathVariable("userId") String userId){
        return "hello user  " + userId + "  ";
    }
}

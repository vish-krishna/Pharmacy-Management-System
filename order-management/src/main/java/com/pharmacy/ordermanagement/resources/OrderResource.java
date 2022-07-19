package com.pharmacy.ordermanagement.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderResource {

    @RequestMapping("/order")
    public String getOrder(){
        return  "hello order";
    }
}

package com.pharmacy.suppliermanagement.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierResource {

    @RequestMapping("/supplier")
    public String getSupplierInfo(){
        return "Hello Supplier ";
    }
}

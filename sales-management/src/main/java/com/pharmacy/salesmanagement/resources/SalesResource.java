package com.pharmacy.salesmanagement.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesResource {

    @RequestMapping("/sales")
    public String getSalesInfo(){
        return "hello Sales Information ";
    }

}

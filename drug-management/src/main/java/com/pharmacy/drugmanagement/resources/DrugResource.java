package com.pharmacy.drugmanagement.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrugResource {

    @RequestMapping("/drug")
    public String getDrug(){
        return "hello drug ";
    }
}

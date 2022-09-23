package com.pharmacy.salesmanagement.controller;

import com.pharmacy.salesmanagement.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sales")
@CrossOrigin("*")
public class SalesController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/total")
    public Double totalSales(){
        ResponseEntity<Orders[]> response = restTemplate.getForEntity("http://order-management/order", Orders[].class);
        Orders[] o = response.getBody();

        Double totalSale = 0.0;
        for (Orders orders1 : o)
            totalSale += orders1.getTotalPrice();
        return totalSale;
    }


    @GetMapping("/{doctorId}")
    public Double SaleByDocId(@PathVariable("doctorId") String id) {
        ResponseEntity<Orders[]> response =
                restTemplate.getForEntity(
                        "http://order-management/order/byDoctorId/" + id,
                        Orders[].class);
        Orders[] o = response.getBody();
        Double totalSale = 0.0;
        for (Orders orders : o)
            totalSale += orders.getTotalPrice();
        return totalSale;
    }
}

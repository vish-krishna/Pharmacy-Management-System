package com.pharmacy.salesmanagement.controller;

import com.pharmacy.salesmanagement.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/total")
    public Integer TotalSale(){
        ResponseEntity<Orders[]> response =
                restTemplate.getForEntity(
                        "http://order-management/order/",
                        Orders[].class);
        Orders[] o = response.getBody();
//        List<Orders> orders = restTemplate.get;
        int totalSale = 0;

        for(Orders orders: o)
        {
            totalSale+=orders.getTotalPrice();
        }
        return totalSale;
    }
}

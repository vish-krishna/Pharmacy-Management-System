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
    @GetMapping("/sales/total")
    public Double TotalSales() {
        ResponseEntity<Orders[]> response = restTemplate.getForEntity(
                "http://localhost:8064/order/orders",
                Orders[].class);
        Orders[] o = response.getBody();
        Double totalSale = 0.0;
        for (Orders orders1 : o)
            totalSale += orders1.getTotalPrice();
        return totalSale;
    }

    @GetMapping("/{drugId}")
    public Double SaleByDocId(@PathVariable("drugId") String id) {
        ResponseEntity<Orders[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8064/order/" + id,
                        Orders[].class);
        Orders[] o = response.getBody();
        Double totalSale = 0.0;

        for (Orders orders : o)
            totalSale += orders.getTotalPrice();
        return totalSale;
    }
}

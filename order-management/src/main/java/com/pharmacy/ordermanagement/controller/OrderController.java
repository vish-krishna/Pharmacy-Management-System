package com.pharmacy.ordermanagement.controller;

import com.pharmacy.ordermanagement.models.Drug;
import com.pharmacy.ordermanagement.models.OrderedDrug;
import com.pharmacy.ordermanagement.models.Orders;
import com.pharmacy.ordermanagement.models.OrderProcessing;
import com.pharmacy.ordermanagement.services.DrugService;
import com.pharmacy.ordermanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DrugService drugService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order")
    public ResponseEntity getOrder() {
        try {
            return ResponseEntity.ok(orderService.getOrder());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }


    @PostMapping("/order")
    public ResponseEntity createOrder(@RequestBody OrderProcessing orderProcessing) {

        /*
         * order processing
         *   order id
         *   doctor id
         *   list<OrderedDrug> -->  drug id, quantity
         */


        List<OrderedDrug> orderedDrugList = orderProcessing.getOrderedDrugList();
//        List<OrderedDrug> orderedDrugList = new ArrayList<>();
//        OrderedDrug od = new OrderedDrug("d1",1);
//        OrderedDrug od1 = new OrderedDrug("d2",1);
//
//        orderedDrugList.add(od);
//        orderedDrugList.add(od1);
        List<Drug> drugList = new ArrayList<>();

        double totalPrice = 0;

        Orders order1 = new Orders();
        order1.setOrderId(orderProcessing.getOrderId());
        order1.setDoctorId(orderProcessing.getDoctorId());
        order1.setPickedUp(false);
        order1.setVerified(false);


        for(OrderedDrug orderedDrug : orderedDrugList){
            Drug drug = restTemplate.getForObject("http://drug-management/drug/"+orderedDrug.getDrugId(),Drug.class);
            drug.setDrugQuantity(orderedDrug.getQuantity());
            totalPrice += drug.getPrice() * orderedDrug.getQuantity();
            drugList.add(drug);
            drug.setOrders(order1);
            //drugService.saveDrug(drug);
        }




        order1.setDrugList(drugList);
        order1.setTotalPrice(totalPrice);

        Orders orders = orderService.saveOrder(order1);
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
        //





//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("No order created");


/*
        List<OrderedDrug> orderedDrugList = orderProcessing.getOrderedDrugList();
        List<Drug> drugList = new ArrayList<>();
        double totalPrice = 0;
        System.out.println("drug +++++++++++" + "" + "\n" + "\n");
        for(OrderedDrug orderedDrug : orderedDrugList){
            Drug drug = restTemplate.getForObject("http://drug-management/drug/"+orderedDrug.getDrugId(),Drug.class);
            drugList.add(drug);
            drugService.saveDrug(drug);
            totalPrice += drug.getPrice() * orderedDrug.getQuantity();
        }

        //Drug drug = new Drug("d1","drugName1",5,new Date(),105,"b1");
        System.out.println("drug +++++++++++" + "" + "\n" + "\n" + "  ");

        Orders order = new Orders();
        order.setOrderId(orderProcessing.getOrderId());
        order.setDoctorId(orderProcessing.getDoctorId());
        order.setDrugList(drugList);
        order.setPickedUp(false);
        order.setVerified(false);

        order.setTotalPrice(totalPrice);

        Orders orders = orderService.saveOrder(order);
        return new ResponseEntity<>(orders,HttpStatus.CREATED);

//        try {
//            return ResponseEntity.ok(orderService.saveOrder(order));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("No order created");
//        }*/
    }
    @PutMapping("/order/{orderId}")
    public ResponseEntity updateDrug(@RequestBody Orders order, @PathVariable("orderId") String id) {
        try {
            return ResponseEntity.ok(orderService.updateOrder(order,id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") String id) {
        try {
            return ResponseEntity.ok(orderService.deleteOrder(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }


}
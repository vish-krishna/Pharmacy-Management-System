package com.pharmacy.ordermanagement.controller;

import com.pharmacy.ordermanagement.models.*;
import com.pharmacy.ordermanagement.services.OrderService;
import com.pharmacy.ordermanagement.services.impl.EmailSenderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EmailSenderService senderService;

    @GetMapping()
    @ApiOperation(value = "get all orders",response = Orders.class)
    public ResponseEntity getOrder() {
        try {
            return ResponseEntity.ok(orderService.getOrder());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }

    @PostMapping()
    @ApiOperation(value = "generating order",notes = "provide necessary details to create a order",response = Orders.class)
    public ResponseEntity createOrder(@RequestBody OrderProcessing orderProcessing) {

        // getting the list of drug requested by doctor
        //List<Drug> drugListRequested = orders.getDrugList();
        List<OrderedDrug> drugListRequested = orderProcessing.getOrderedDrugList();

        // creating new drug list which are available and order get placed
        List<Drug> drugList = new ArrayList<>();

        //creating order

        Orders orders = new Orders();

        Users doctor = restTemplate.getForObject("http://user-management/user/"+orderProcessing.getDoctorId(),Users.class);

        double totalPrice = 0;

        for(OrderedDrug d : drugListRequested){
            // getting the drug info from drug management microservice
            Drug drug = restTemplate.getForObject("http://drug-management/drug/"+d.getDrugId(),Drug.class);
            if(drug != null && drug.getDrugQuantity() >= d.getQuantity()){
                // store quantity to be update in drug management
                int updatedQuantity = drug.getDrugQuantity() - d.getQuantity();

                // calaculating total price of all drugs
                totalPrice += drug.getPrice() * d.getQuantity();

                // update drug in drug management
                drug.setDrugQuantity(updatedQuantity);
                restTemplate.put("http://drug-management/drug/quantity/"+d.getDrugId(),drug,Drug.class);

                // update ordered drug quantity
                drug.setDrugQuantity(d.getQuantity());

                // adding drug into order
                drugList.add(drug);

            }else{

                // update drug in drug management
                for(Drug drug1 : drugList){
                    Drug drug2 = restTemplate.getForObject("http://drug-management/drug/"+drug1.getDrugId(),Drug.class);
                    drug2.setDrugQuantity(drug1.getDrugQuantity()+drug2.getDrugQuantity());
                    restTemplate.put("http://drug-management/drug/quantity/"+drug2.getDrugId(),drug2,Drug.class);
                }

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drug not availabe");
            }
        }

        // setting orders default properties
        orders.setDoctorId(orderProcessing.getDoctorId());
        orders.setPickedUp(false);
        orders.setVerified(false);
        orders.setDrugList(drugList);
        orders.setTotalPrice(totalPrice);

        // saving the order in database
        orders = orderService.saveOrder(orders);
        return new ResponseEntity<>(orders,HttpStatus.CREATED);

//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body( senderService.sendSimpleEmail(doctor.getUserEmail(),
//                        "PHARMACARE: New Order ",
//                        "Hey " + doctor.getUserName()+"      " +
//                                "You have ordered" + orders.toString() ));

    }
    @PutMapping("/{orderId}")
    public ResponseEntity updateDrug(@RequestBody Orders order, @PathVariable("orderId") String id) {
        try {
            return ResponseEntity.ok(orderService.updateOrder(order,id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") String id) {
        try {
            return ResponseEntity.ok(orderService.deleteOrder(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }


    @GetMapping("/pickUp/{orderId}")
    @ApiOperation(value = "orderPickUp",notes = "Order marked as placed in picked up section by pharmacist")
    public ResponseEntity orderPickUp(@PathVariable("orderId") String orderId) {
        try {
            boolean flag = orderService.pickUpOrder(orderId);
            if(flag == true) {
                return ResponseEntity.status(HttpStatus.OK).body("order added to picked-up section");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No order found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL_SERVER_ERROR");
        }
    }

    @GetMapping("/verify/{orderId}")
    @ApiOperation(value = "verify the order",notes = "Order verify by pharmacist")
    public ResponseEntity verifyOrder(@PathVariable("orderId") String orderId) {
        try {
            boolean flag = orderService.verifyOrder(orderId);
            if(flag) {
                return ResponseEntity.status(HttpStatus.OK).body("order verified");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No order found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL_SERVER_ERROR");
        }
    }




    @GetMapping("/{id}")
    @ApiOperation(value = "Get details of a order",notes = "Get details of order by order id")
    public ResponseEntity getOrderById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }




    // helper methods


    @GetMapping("/byPickedUp/{flag}")
    public ResponseEntity findByPickedUp(@PathVariable("flag") boolean flag){
        try {
            return ResponseEntity.ok(orderService.findByPickedUp(flag));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }
    }

    @GetMapping("/byVerified/{flag}")
    public ResponseEntity findByVerified(@PathVariable("flag") boolean flag){
        try {
            return ResponseEntity.ok(orderService.findByVerified(flag));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }

    }
    @GetMapping("/byDoctorId/{id}")
    public ResponseEntity findByDoctorId(@PathVariable("id") String id){
        try {
            return ResponseEntity.ok(orderService.findByDoctorId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No order found");
        }

    }

}
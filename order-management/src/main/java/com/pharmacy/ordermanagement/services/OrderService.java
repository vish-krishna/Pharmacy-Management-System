package com.pharmacy.ordermanagement.services;

import com.pharmacy.ordermanagement.models.Drug;
import com.pharmacy.ordermanagement.models.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List<Orders> getOrder();

    Orders getOrderById(String s);

    public Orders saveOrder(Orders order);

    public Orders updateOrder(Orders order, String id);

    public String deleteOrder(String id);


}
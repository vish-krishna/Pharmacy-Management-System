package com.pharmacy.ordermanagement.services.impl;

import com.pharmacy.ordermanagement.dao.OrderRepository;
import com.pharmacy.ordermanagement.models.Drug;
import com.pharmacy.ordermanagement.models.Orders;
import com.pharmacy.ordermanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> getOrder() {
        List<Orders> order = orderRepository.findAll();
        return order;
    }

    @Override
    public Orders getOrderById(String s) {
        return  orderRepository.findById(s).get();
    }

    @Override
    public Orders saveOrder(Orders order) {
        Orders orders= orderRepository.save(order);
        return orders;
    }

    @Override
    public Orders updateOrder(Orders order, String id) {
        Orders o= orderRepository.findById(id).get();
        o.setOrderId(id);
        o.setDoctorId(order.getDoctorId());
        o.setOrderId(order.getOrderId());
        o.setTotalPrice(order.getTotalPrice());
        o.setPickedUp(order.isPickedUp());
        o.setVerified(order.isVerified());
        orderRepository.save(o);
        return o;
    }

    @Override
    public String deleteOrder(String id) {
        orderRepository.deleteById(id);
        return id;
    }


}

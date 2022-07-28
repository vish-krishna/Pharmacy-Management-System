package com.pharmacy.ordermanagement.dao;

import com.pharmacy.ordermanagement.models.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Orders,String> {

   public List<Orders> findAll();

   @Override
   Optional<Orders> findById(String s);
}

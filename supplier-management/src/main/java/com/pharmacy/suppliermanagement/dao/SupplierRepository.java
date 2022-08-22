package com.pharmacy.suppliermanagement.dao;

import com.pharmacy.suppliermanagement.models.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier,Long> {
    public List<Supplier> findAll();

    Supplier findSupplierBySupplierId(Long id);
}

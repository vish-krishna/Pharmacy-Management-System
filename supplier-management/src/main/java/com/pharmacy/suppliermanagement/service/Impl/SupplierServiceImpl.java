package com.pharmacy.suppliermanagement.service.Impl;

import com.pharmacy.suppliermanagement.models.Supplier;
import com.pharmacy.suppliermanagement.dao.SupplierRepository;
import com.pharmacy.suppliermanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getSupplier() {
        List<Supplier> supplier = supplierRepository.findAll();
        return supplier;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        Supplier suppliers = supplierRepository.save(supplier);
        return suppliers;
    }

    @Override
    public Supplier updateSupplier(Supplier supplier,long id) {
        Supplier s = supplierRepository.findById(id).get();
        s.setSupplierId(id);
        s.setSupplierName(supplier.getSupplierName());
        s.setSupplierContact(supplier.getSupplierContact());
        s.setSupplierEmail(supplier.getSupplierEmail());
        supplierRepository.save(s);
        return s;
    }

    @Override
    public long deleteSupplier(long id) {
        supplierRepository.deleteById(id);
        return id;
    }

    @Override
    public Supplier findSupplierById(long id) {
        Supplier s = supplierRepository.findSupplierBySupplierId(id);
        return s;
    }
}
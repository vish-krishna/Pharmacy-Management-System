package com.pharmacy.suppliermanagement.service;

import com.pharmacy.suppliermanagement.models.Supplier;

import java.util.List;

public interface SupplierService {

    public List<Supplier> getSupplier();

    public Supplier saveSupplier(Supplier supplier);

    public Supplier updateSupplier(Supplier supplier, long id);

    public long deleteSupplier(long id);
    public Supplier findSupplierById(long id);
}

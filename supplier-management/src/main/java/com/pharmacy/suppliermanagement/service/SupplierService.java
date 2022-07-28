package com.pharmacy.suppliermanagement.service;

import com.pharmacy.suppliermanagement.models.Supplier;

import java.util.List;

public interface SupplierService {

    public List<Supplier> getSupplier();

    public Supplier saveSupplier(Supplier supplier);

    public Supplier updateSupplier(Supplier supplier, String id);

    public String deleteSupplier(String id);
}

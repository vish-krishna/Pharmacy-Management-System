package com.pharmacy.suppliermanagement.controller;

import com.pharmacy.suppliermanagement.models.Supplier;
import com.pharmacy.suppliermanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> getUsers() {
        try {
            return ResponseEntity.ok(supplierService.getSupplier());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/supplier")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.saveSupplier(supplier));
    }

    @PutMapping("/supplier/{supplierId}")
    public ResponseEntity<Supplier> updateUser(@RequestBody Supplier supplier, @PathVariable("supplierId") String id) {
        return ResponseEntity.ok(supplierService.updateSupplier(supplier, id));
    }

    @DeleteMapping("/supplier/{supplierId}")
    public ResponseEntity<String> deleteSupplier(@PathVariable("supplierId") String id) {
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }
}


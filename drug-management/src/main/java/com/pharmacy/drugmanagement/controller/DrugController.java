package com.pharmacy.drugmanagement.controller;

import com.pharmacy.drugmanagement.Entity.Drug;
import com.pharmacy.drugmanagement.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DrugController {
    @Autowired
    private DrugService drugService;


    @GetMapping("/drug")
    public ResponseEntity<List<Drug>> getUsers() {
        try {
            return ResponseEntity.ok(drugService.getDrug());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/drug")
    public ResponseEntity<Drug> createDrug(@RequestBody Drug drug) {
        return ResponseEntity.ok(drugService.saveDrug(drug));
    }

    @PutMapping("/drug/{drugId}")
    public ResponseEntity<Drug> updateUser(@RequestBody Drug drug, @PathVariable("drugId") String id) {
        return ResponseEntity.ok(drugService.updateDrug(drug, id));
    }

    @DeleteMapping("/drug/{drugId}")
    public ResponseEntity<String> deleteDrug(@PathVariable("drugId") String id) {
        return ResponseEntity.ok(drugService.deleteDrug(id));
    }
}




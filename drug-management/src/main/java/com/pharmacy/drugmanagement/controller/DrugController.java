package com.pharmacy.drugmanagement.controller;

import com.pharmacy.drugmanagement.models.Drug;
import com.pharmacy.drugmanagement.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping("/drug")
    public ResponseEntity<List<Drug>> getDrugs() {
        try {
            List<Drug> d = drugService.getDrug();
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/drug/{drugId}")
    public ResponseEntity<Drug> getDrug(@PathVariable("drugId") String drugId) {
        try {
            return ResponseEntity.ok(drugService.getDrugById(drugId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/drug")
    public ResponseEntity<Drug> createDrug(@RequestBody Drug drug) {
        try{
            Drug drug1 = drugService.saveDrug(drug);
            return ResponseEntity.ok(drug1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/drug/{drugId}")
    public ResponseEntity<Drug> updateDrug(@RequestBody Drug drug, @PathVariable("drugId") String id) {
       try{
           Drug drug1 = drugService.updateDrug(drug, id);
           return ResponseEntity.ok(drug1);
       }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @PutMapping("/drug/quantity/{drugId}")
    public ResponseEntity<Drug> updateDrugQuantity(@RequestBody Drug drug, @PathVariable("drugId") String id) {
        try{
            Drug d = drugService.updateDrugQuantity(drug, id);
            return ResponseEntity.ok(d);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/drug/{drugId}")
    public ResponseEntity<String> deleteDrug(@PathVariable("drugId") String id) {
        try{
            String drugId = drugService.deleteDrug(id);
            return ResponseEntity.ok(drugId);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
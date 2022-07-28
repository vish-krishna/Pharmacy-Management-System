package com.pharmacy.drugmanagement.service.Impl;

import com.pharmacy.drugmanagement.models.Drug;
import com.pharmacy.drugmanagement.dao.DrugRespository;
import com.pharmacy.drugmanagement.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugRespository drugRepository;

    @Override
    public List<Drug> getDrug() {
        List<Drug> drug = drugRepository.findAll();
        return drug;
    }

    @Override
    public Drug getDrugById(String drugId) {
        return drugRepository.findById(drugId).get();
    }


    @Override
    public Drug saveDrug(Drug drug) {
        Drug drugs = drugRepository.save(drug);
        return drugs;
    }

    @Override
    public Drug updateDrug(Drug drug,String id) {
        Drug d = drugRepository.findById(id).get();
        d.setDrugId(id);
        d.setDrugName(drug.getDrugName());
        d.setDrugQuantity(drug.getDrugQuantity());
        d.setBatchId(drug.getBatchId());
        d.setPrice(drug.getPrice());
        d.setExpiryDate(drug.getExpiryDate());
        drugRepository.save(d);
        return d;
    }

    @Override
    public String deleteDrug(String id) {
        drugRepository.deleteById(id);
        return id;
    }
}
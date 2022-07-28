package com.pharmacy.ordermanagement.services.impl;

import com.pharmacy.ordermanagement.dao.DrugRepository;
import com.pharmacy.ordermanagement.models.Drug;
import com.pharmacy.ordermanagement.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Override
    public void saveDrug(Drug drug) {
        drugRepository.save(drug);
    }
}

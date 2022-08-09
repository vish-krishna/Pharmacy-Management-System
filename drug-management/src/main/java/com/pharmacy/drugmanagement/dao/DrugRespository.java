package com.pharmacy.drugmanagement.dao;

import com.pharmacy.drugmanagement.models.Drug;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DrugRespository extends CrudRepository<Drug,String> {

        public List<Drug> findAll();


    }


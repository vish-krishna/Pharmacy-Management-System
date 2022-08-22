package com.pharmacy.drugmanagement.dao;

import com.pharmacy.drugmanagement.models.Drug;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DrugRepository extends CrudRepository<Drug,String> {
        List<Drug> findAll();
        Drug findByDrugId(String id);
}


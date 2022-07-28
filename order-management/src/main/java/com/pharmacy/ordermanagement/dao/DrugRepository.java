package com.pharmacy.ordermanagement.dao;

import com.pharmacy.ordermanagement.models.Drug;
import org.springframework.data.repository.CrudRepository;

public interface DrugRepository extends CrudRepository<Drug,String> {
}

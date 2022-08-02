package com.pharmacy.usermanagement.dao;

import com.pharmacy.usermanagement.models.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin,String> {

    public List<Admin> findAll();

}

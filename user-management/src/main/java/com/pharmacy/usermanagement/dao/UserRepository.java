package com.pharmacy.usermanagement.dao;

import com.pharmacy.usermanagement.models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users,String> {

    public List<Users> findAll();
    public Users findByUserEmail(String email);

}

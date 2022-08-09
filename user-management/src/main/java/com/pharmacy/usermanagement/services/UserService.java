package com.pharmacy.usermanagement.services;

import com.pharmacy.usermanagement.models.Users;

import java.util.List;

public interface UserService {


    public List<Users> getUsers();

    public Users saveUser(Users users);

    public Users updateUser(Users users, String id);

    public String deleteUser(String id);
}

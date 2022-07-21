package com.pharmacy.usermanagement.services.impl;

import com.pharmacy.usermanagement.dao.UserRepository;
import com.pharmacy.usermanagement.entity.Users;
import com.pharmacy.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getUsers() {
        List<Users> users = userRepository.findAll();
        return users;
    }

    @Override
    public Users saveUser(Users user) {
        Users users = userRepository.save(user);
        return users;
    }

    @Override
    public Users updateUser(Users users,String id) {
        Users u = userRepository.findById(id).get();
        u.setUser_Id(id);
        u.setUser_password(users.getUser_password());
        u.setUser_contact(users.getUser_contact());
        u.setUser_email(users.getUser_email());
        u.setUser_name(users.getUser_name());
        userRepository.save(u);
        return u;
    }

    @Override
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return id;
    }
}

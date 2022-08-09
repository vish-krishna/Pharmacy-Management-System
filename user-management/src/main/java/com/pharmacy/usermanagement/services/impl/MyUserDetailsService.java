package com.pharmacy.usermanagement.services.impl;

import com.pharmacy.usermanagement.dao.UserRepository;
import com.pharmacy.usermanagement.models.MyUserDetails;
import com.pharmacy.usermanagement.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUserEmail(username);
        if(users != null){
            return new MyUserDetails(users);
        }else{
            throw new UsernameNotFoundException("Not found - "+username);
        }
    }
}

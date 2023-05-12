package com.example.snoopsht.service;


import com.example.snoopsht.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuatomUserDetailsService implements UserDetailsService, UserService {
    @Override
    public User createUser(User newUser) {
        return null;
    }

    @Override
    public User createAdmin(User newAdmin) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public boolean CreateFirstAdmin() {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

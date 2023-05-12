package com.example.snoopsht.service;

import com.example.snoopsht.models.User;

import java.util.List;

public interface UserService {
    User createUser(User newUser);

    User createAdmin(User newAdmin);

    List<User> findAllUsers();

    boolean CreateFirstAdmin();
}

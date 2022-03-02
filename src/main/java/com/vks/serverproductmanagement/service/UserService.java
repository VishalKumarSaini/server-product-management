package com.vks.serverproductmanagement.service;

import com.vks.serverproductmanagement.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

    //Optional<User> findByUsername(String username);

    User findByUserName(String username);

    List<User> findAllUser();

    Long countAllUser();
}

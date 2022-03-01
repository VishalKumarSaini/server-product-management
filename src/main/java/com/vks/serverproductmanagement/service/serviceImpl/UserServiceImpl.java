package com.vks.serverproductmanagement.service.serviceImpl;

import com.vks.serverproductmanagement.model.User;
import com.vks.serverproductmanagement.repository.UserRepository;
import com.vks.serverproductmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    public PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public User saveUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username).orElse(null);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Long countAllUser() {
        return userRepository.count();
    }

}

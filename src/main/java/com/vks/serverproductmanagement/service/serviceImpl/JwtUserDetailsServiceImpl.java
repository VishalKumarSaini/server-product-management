package com.vks.serverproductmanagement.service.serviceImpl;

import com.vks.serverproductmanagement.model.User;
import com.vks.serverproductmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {


   private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }
        //username: abc, password:pass
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>()); // How to pass decrypted password in order to make JWT token

        }
    }


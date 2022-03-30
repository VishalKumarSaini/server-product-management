package com.vks.serverproductmanagement.repository;

import com.vks.serverproductmanagement.model.User;
import com.vks.serverproductmanagement.model.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.vks.serverproductmanagement.model.UserRole.ROLE_ADMIN;
import static com.vks.serverproductmanagement.model.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
     UserRepository userRepository;

    @Test
    void findByUsername() {
        User user = new User(1L,"user vks","user","pass", ROLE_USER);
    userRepository.save(user);

        Optional<User> fetchedUser = userRepository.findByUsername("user");
        assertThat(fetchedUser.get()).isEqualTo(user);
    }

    @AfterEach
    void tearDown() {
        //userRepository.deleteAll();

        System.out.println("After Each called");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before Each called");
    }
}
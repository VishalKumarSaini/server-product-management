package com.vks.serverproductmanagement.service.serviceImpl;

import com.vks.serverproductmanagement.model.User;
import com.vks.serverproductmanagement.model.UserRole;
import com.vks.serverproductmanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Unit Test For UserServiceImpl")
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRepository userRepository;

    User user = new User(1L, "vks", "username", "password", UserRole.ROLE_USER);


    @Nested
    @DisplayName("Unit Test For FindByUserName Method")
    class FindByUserNameTests {
        @Test
        @DisplayName("Happy Path")
        void findByUsername() {

            when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(Optional.of(user));

            User userResponse = userService.findByUserName("vishal");

            Assertions.assertEquals(userResponse, user);
        }

        @Test
        @DisplayName("Error case")
        void findByUsernameWithError() {

            when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

//        User userResponse = userService.findByUserName("vishal");
            Assertions.assertThrows(NoSuchElementException.class, () -> userService.findByUserName("vishal"));


        }
    }


}
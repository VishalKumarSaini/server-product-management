package com.vks.serverproductmanagement;

import com.vks.serverproductmanagement.model.User;
import com.vks.serverproductmanagement.model.UserRole;
import com.vks.serverproductmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerProductManagementApplication { //implements CommandLineRunner { //in order to make entries in db

//    final UserService userService;
//
//    @Autowired
//    public ServerProductManagementApplication(UserService userService) {
//        this.userService = userService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ServerProductManagementApplication.class, args);
    }

/*    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("user");
        user.setPassword("pass");
        user.setUserRole(UserRole.User);
        user.setName("user");
        userService.saveUser(user);
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("pass");
        adminUser.setUserRole(UserRole.Admin);
        adminUser.setName("adminUser");
        userService.saveUser(adminUser);
        }
        */

}

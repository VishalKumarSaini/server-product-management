package com.vks.serverproductmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vks.*")
public class ServerProductManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerProductManagementApplication.class, args);
    }

}

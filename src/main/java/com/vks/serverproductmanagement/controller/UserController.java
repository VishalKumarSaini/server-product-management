package com.vks.serverproductmanagement.controller;

import com.vks.serverproductmanagement.model.Transaction;
import com.vks.serverproductmanagement.model.User;
import com.vks.serverproductmanagement.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vks.serverproductmanagement.service.ProductService;
import com.vks.serverproductmanagement.service.TransactionService;
import com.vks.serverproductmanagement.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private UserService userService;
    private ProductService productService;
    private TransactionService transactionService;

    @Autowired
    public UserController(UserService userService, ProductService productService, TransactionService transactionService) {
        this.userService = userService;
        this.productService = productService;
        this.transactionService = transactionService;
    }


    @PostMapping(value = "register")
    public ResponseEntity<?> registerUser(@RequestBody(required = true) User user) {
        if (userService.findByUserName(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setUserRole(UserRole.ROLE_USER);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("login")
    public ResponseEntity<?> userLogin(Principal principal) {
        if (principal == null || principal.getName() == null) {
            //logout will be here too
            return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<>(userService.findByUserName(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("purchase")
    public ResponseEntity<?> buyProduct(@RequestBody Transaction transaction) {
        transaction.setPurchaseDate(LocalDateTime.now());
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping("products")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


}

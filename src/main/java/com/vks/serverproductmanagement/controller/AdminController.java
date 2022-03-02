package com.vks.serverproductmanagement.controller;

import com.vks.serverproductmanagement.model.Product;
import com.vks.serverproductmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vks.serverproductmanagement.service.ProductService;
import com.vks.serverproductmanagement.service.TransactionService;
import com.vks.serverproductmanagement.service.UserService;
import com.vks.serverproductmanagement.util.StringResponse;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    final ProductService productService;
    final UserService userService;
    final TransactionService transactionService;

    @Autowired
    public AdminController(ProductService productService, UserService userService, TransactionService transactionService) {
        this.productService = productService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PutMapping("user-update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User existUser = userService.findByUserName(user.getUsername());
        if (existUser != null && existUser.getId().equals(user.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }


    @PostMapping("user-delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        User existUser = userService.findByUserName(user.getUsername());
        if (existUser == null || !Objects.equals(existUser.getId(), user.getId())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.deleteUserById(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("user-all")
    public ResponseEntity<?> allUser() {
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    @GetMapping("user-count")
    public ResponseEntity<?> countAllUser() {
        Long userCount = userService.countAllUser();
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(userCount.toString());
        return new ResponseEntity<>(stringResponse, HttpStatus.OK);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("product-all")
    public ResponseEntity<?> allProduct() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


    @GetMapping("product-count")
    public ResponseEntity<?> countAllProduct() {
        Long productCount = productService.countAllProducts();
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(productCount.toString());
        return new ResponseEntity<>(stringResponse, HttpStatus.OK);
    }

    @PostMapping("product-create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("product-update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        Optional<Product> existProduct = productService.findProduct(product.getId());
        if (Objects.isNull(existProduct)) {
            return new ResponseEntity<>("Product doesn't exist", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    @PostMapping("product-delete")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
        Optional<Product> existProduct = productService.findProduct(product.getId());
        if (Objects.isNull(existProduct)) {
            return new ResponseEntity<>("Product doesn't exist", HttpStatus.CONFLICT);
        }
        productService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("transaction-all")
    public ResponseEntity<?> allTransaction() {
        return new ResponseEntity<>(transactionService.getAllTransaction(), HttpStatus.OK);
    }

    @GetMapping("transaction-count")
    public ResponseEntity<?> countTransaction() {//todo: change respone into Class
//        Long transactionCount= transactionService.countTransaction();
//        StringResponse stringResponse= new StringResponse();
//        stringResponse.setResponse(transactionCount.toString());
//        return  new ResponseEntity<>(stringResponse,HttpStatus.OK);
        return new ResponseEntity<>(transactionService.countTransaction(), HttpStatus.OK);
    }
}

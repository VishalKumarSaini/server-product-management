package com.vks.serverproductmanagement.controller;

import com.vks.serverproductmanagement.model.User;
import com.vks.serverproductmanagement.service.ProductService;
import com.vks.serverproductmanagement.service.serviceImpl.JwtUserDetailsServiceImpl;
import com.vks.serverproductmanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    private final JwtUserDetailsServiceImpl jwtUserDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ProductService productService;

    @Autowired
    public JwtController(JwtUserDetailsServiceImpl jwtUserDetailsService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, ProductService productService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.productService = productService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody User jwtRequestUser) throws Exception { //Username, Password required only

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequestUser.getUsername(),
                    jwtRequestUser.getPassword()
                    )
            );

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Username not found");
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails existingUserDetails = jwtUserDetailsService.loadUserByUsername(jwtRequestUser.getUsername());

        System.out.println("Username: " + jwtRequestUser.getUsername());
        System.out.println("Password: " + jwtRequestUser.getPassword());


        String token = jwtUtil.generateToken(existingUserDetails);
        System.out.println("JWT token : " + token);

        return ResponseEntity.ok("token :"+token);

    }


    @GetMapping("/welcome")
    public String welcomeJwtAuthorised() {
        return "JWT token has been verified\n You are Welcome Sir";
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

}

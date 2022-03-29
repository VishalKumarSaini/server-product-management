package com.vks.serverproductmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

//    public User(Long id,String name, String username, String password, UserRole roleUser) {
//        this.id=id;
//        this.name=name;
//        this.password=password;
//        this.username=username;
//        this.userRole= UserRole.valueOf(String.valueOf(roleUser));
//    }
}

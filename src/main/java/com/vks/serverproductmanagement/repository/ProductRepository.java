package com.vks.serverproductmanagement.repository;

import com.vks.serverproductmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Product getById(Long productId);
}

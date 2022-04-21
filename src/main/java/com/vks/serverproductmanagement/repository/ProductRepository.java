package com.vks.serverproductmanagement.repository;

import com.vks.serverproductmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Product getById(Long productId);
}

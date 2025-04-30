package com.example.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.products.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    // List<Product> findByAvailableTrue();
}


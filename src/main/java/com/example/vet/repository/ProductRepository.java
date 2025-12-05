package com.example.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.vet.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findBySupplierIdSupplier(Integer supplierId);
    List<Product> findByProductNameIgnoreCase(String productName);
}

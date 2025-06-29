package com.Product.ProductRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Product.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

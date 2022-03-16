package com.dia.backend.data.repository;

import com.dia.backend.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author roed
 */
public interface ProductRepository extends JpaRepository<Product, Long> {}

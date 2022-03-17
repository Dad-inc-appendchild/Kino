package com.dia.backend.view.controller;

import com.dia.backend.data.repository.ProductRepository;
import com.dia.backend.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products") // prefix for endpoints
public class ProductController {
  @Autowired ProductRepository productRepository;

  @GetMapping("")
  public List<Product> displayProducts() {
    return productRepository.findAll();
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void postProduct(@RequestBody Product product) {
    productRepository.save(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable Long id, @RequestBody Product product) {
    Optional<Product> existingProduct = productRepository.findById(id);
    if (existingProduct.isPresent()) {
      productRepository.save(product);
      return new ResponseEntity<>(product, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    try {
      productRepository.deleteById(id);
      return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
    } catch (Exception err) {
      return new ResponseEntity<>("Error deleting: " + id, HttpStatus.NO_CONTENT);
    }
  }
}

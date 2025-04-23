package com.example.ecommerce.controller;

import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.service.serviceinterfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/home")
    public ResponseEntity<Object> getAllProducts() {
        try {
            return ResponseEntity.ok(productService.getAllProducts());
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/id")
    public ResponseEntity<Object> getProductById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<Object> searchProducts(@RequestParam String name) {
        try {
            return ResponseEntity.ok(productService.searchProducts(name));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
} 
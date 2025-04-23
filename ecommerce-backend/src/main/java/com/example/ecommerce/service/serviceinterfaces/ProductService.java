package com.example.ecommerce.service.serviceinterfaces;

import com.example.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> searchProducts(String name);
}

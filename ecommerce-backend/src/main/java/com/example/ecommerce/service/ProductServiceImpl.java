package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exceptions.CustomException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.serviceinterfaces.ProductService;
import com.example.ecommerce.utils.EcommerceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new CustomException(EcommerceConstants.PRODUCTS_NOT_FOUND);
        }
    }

    @Override
    public Product getProductById(Long id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new CustomException(EcommerceConstants.PRODUCTS_NOT_FOUND));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Product> searchProducts(String name) {
        try {
            return productRepository.searchProducts(name);
        } catch (Exception e) {
            throw new CustomException(EcommerceConstants.PRODUCTS_NOT_FOUND);
        }
    }
}
